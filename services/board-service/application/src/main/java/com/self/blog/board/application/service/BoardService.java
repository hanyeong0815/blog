package com.self.blog.board.application.service;

import com.self.blog.board.application.aop.ViewCountUp;
import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.mapper.BoardMapper;
import com.self.blog.board.application.repository.*;
import com.self.blog.board.application.usecase.*;
import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListResponse;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListView;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardRecommendListView;
import com.self.blog.board.application.usecase.data.BoardUpdateDto.BoardFindForUpdateResponse;
import com.self.blog.board.application.utils.OgFactory;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardElasticsearch;
import com.self.blog.board.domain.BoardView;
import com.self.blog.board.readmodels.BoardReadModels.BoardFindForUpdateReadModel;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class BoardService implements
        BoardSaveUseCase,
        BoardDetailViewUseCase,
        BoardListViewUseCase,
        BoardFindForUpdateUseCase,
        BoardDeleteUseCase,
        RecommendBoardUseCase
{
    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;
    private final DomainRepository domainRepository;
    private final BoardDefaultSequenceRepository boardDefaultSequenceRepository;
    private final BoardSearchRepository boardSearchRepository;

    private final BoardMapper boardMapper;

    private final S3ImageSaveUseCase s3ImageSaveUseCase;

    OgFactory ogFactory = OgFactory.getInstance();

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8090")
            .build();

    @Override
    public BoardAndViewCountResponse boardSave(Board board, int ogNumber, MultipartFile ogFile) {
        // 유저 도메인 검증을 위한 서버 통신
        String validateDomain = webClient.get()
                .uri(STR."domain/validate/\{board.getUsername()}")
                .retrieve()
                .bodyToMono(String.class)
                .blockOptional().orElseThrow(
                        BoardErrorCode.DEFAULT::defaultException
                );

        // 유저 도메인 검증
        validate(
                !Objects.equals(validateDomain, ""),
                BoardErrorCode.DEFAULT
        );

        // id가 있을 시 수정으로 이동
        if(board.getId() != null) {
            return boardUpdate(board);
        }

        // 글의 대한 순서 추출
        Long defaultSequence = boardDefaultSequenceRepository.CountUpAndGetSequence();
        Long domainSequence = domainRepository.countUpAndGetSequence(board.getDomain());

        String ogFileName;
        String ogThumbnailFileName;

        if (ogNumber == 5) { // 커스텀 이미지 사용
            // 파일 유효성 검사
            validate(
                    ogFile != null && !ogFile.isEmpty(),
                    BoardErrorCode.DEFAULT
            );

            // 파일 업로드
            Map<String, String> result = s3ImageSaveUseCase.imageSave(ogFile);

            ogFileName = result.get("original");
            ogThumbnailFileName = result.get("thumbnail");
        } else { // 샘플 이미지 사용
            // 이미 저장되어있는 파일 이름 호출
            ogFileName = ogFactory.getOgFileName(ogNumber);
            ogThumbnailFileName = ogFactory.getOgFileName(ogNumber);
        }

        Board cloneBoard = board.toBuilder()
                .domain(validateDomain)
                .defaultSequence(defaultSequence)
                .domainSequence(domainSequence)
                .ogFileName(ogFileName)
                .ogThumbnailFileName(ogThumbnailFileName)
                .build();

        Board savedBoard = boardRepository.save(cloneBoard);

        BoardView boardView = BoardView.builder()
                .boardId(savedBoard.getId())
                .viewCount(0)
                .commentAndReplyCount(0)
                .build();
        BoardView savedBoardView = boardViewRepository.save(boardView);

        return boardMapper.from(savedBoard, savedBoardView, savedBoard.getComments());
    }

    @ViewCountUp
    @Override
    public BoardAndViewCountResponse boardDetailView(String boardId, String username, String ViewIp) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(
                        BoardErrorCode.BOARD_NOT_FOUND::defaultException
                );
        BoardView boardView = boardViewRepository.findById(board.getId())
                .orElseThrow(
                        BoardErrorCode.DEFAULT::defaultException
                );

        return boardMapper.from(board, boardView, board.getComments());
    }

    @Override
    public BoardListResponse boardListView(String domain, Pageable pageable) {
        Page<BoardListViewReadModel> boardListViewReadModelsList;

        if(domain == null) {
            boardListViewReadModelsList = boardRepository.findAllBy(pageable);
        } else {
            boardListViewReadModelsList = boardRepository.findByDomain(domain, pageable);
        }

        if (boardListViewReadModelsList.isEmpty()) {
            return BoardListResponse.builder()
                    .totalElement(0L)
                    .totalPage(0)
                    .boardList(List.of())
                    .build();
        }

        return BoardListResponse.builder()
                .boardList(
                        boardListViewReadModelsList.getContent()
                                .stream()
                                .map(board -> {
                                    BoardView boardView = boardViewRepository.findByBoardId(board.id())
                                            .orElseThrow(
                                                    BoardErrorCode.DEFAULT::defaultException
                                            );

                                    return BoardListView.builder()
                                            .boardId(board.id())
                                            .sequence(domain == null ? board.defaultSequence() : board.domainSequence())
                                            .domain(board.domain())
                                            .title(board.title())
                                            .content(board.content())
                                            .username(board.username())
                                            .nickname(board.nickname())
                                            .viewCount(boardView.getViewCount())
                                            .commentCount(boardView.getCommentAndReplyCount())
                                            .createdAt(board.createdAt())
                                            .ogThumbnailFileName(board.ogThumbnailFileName())
                                            .build();
                                })
                                .toList()
                )
                .totalPage(boardListViewReadModelsList.getTotalPages())
                .totalElement(boardListViewReadModelsList.getTotalElements())
                .build();
    }

    @Override
    public BoardFindForUpdateResponse boardFindForUpdate(String boardId, String username) {
        BoardFindForUpdateReadModel boardReadModel = boardRepository.findByIdForUpdate(boardId)
                .orElseThrow(
                        BoardErrorCode.BOARD_NOT_FOUND::defaultException
                );

        validate(
                boardReadModel.username().equals(username),
                BoardErrorCode.DEFAULT
        );

        return boardMapper.from(boardReadModel);
    }

    private BoardAndViewCountResponse boardUpdate(Board board) {
        Board findBoard = boardRepository.findById(board.getId())
                .orElseThrow(
                        BoardErrorCode.BOARD_NOT_FOUND::defaultException
                );

        Board updateBoard = boardRepository.save(
                findBoard.toBuilder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .createdAt(board.getCreatedAt())
                        .build()
        );

        BoardView boardView = boardViewRepository.findByBoardId(updateBoard.getId())
                .orElseThrow(
                        BoardErrorCode.DEFAULT::defaultException
                );

        return boardMapper.from(updateBoard, boardView, updateBoard.getComments());
    }

    @Override
    public boolean boardDelete(String boardId, String username) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(
                        BoardErrorCode.BOARD_NOT_FOUND::defaultException
                );

        validate(
                findBoard.getUsername().equals(username),
                BoardErrorCode.DEFAULT
        );
        boardRepository.save(
                findBoard.toBuilder()
                        .deleted(true)
                        .build()
        );

        return true;
    }

    @Override
    public List<BoardRecommendListView> recommendBoard() throws IOException {
        return boardSearchRepository.search();
    }

    @Scheduled(fixedRate = 60000)
    public void indexBoard() {
        List<BoardElasticsearch> boardList = boardRepository.findAllByDeleted();
        boardSearchRepository.saveAll(boardList);
    }
}
