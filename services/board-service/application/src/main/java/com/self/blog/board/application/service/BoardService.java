package com.self.blog.board.application.service;

import com.self.blog.board.application.aop.ViewCountUp;
import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.mapper.BoardMapper;
import com.self.blog.board.application.repository.BoardDefaultSequenceRepository;
import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.repository.BoardViewRepository;
import com.self.blog.board.application.repository.CategoryRepository;
import com.self.blog.board.application.usecase.*;
import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListResponse;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListView;
import com.self.blog.board.application.usecase.data.BoardUpdateDto.BoardFindForUpdateResponse;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardView;
import com.self.blog.board.readmodels.BoardReadModels.BoardFindForUpdateReadModel;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class BoardService implements
        BoardSaveUseCase,
        BoardDetailViewUseCase,
        BoardListViewUseCase,
        BoardFindForUpdateUseCase,
        BoardDeleteUseCase
{
    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;
    private final CategoryRepository categoryRepository;
    private final BoardDefaultSequenceRepository boardDefaultSequenceRepository;

    private final BoardMapper boardMapper;

    @Override
    public BoardAndViewCountResponse boardSave(Board board) {
        if(board.getId() != null) {
            return boardUpdate(board);
        }

        Long defaultSequence = boardDefaultSequenceRepository.CountUpAndGetSequence();
        Long categorySequence = categoryRepository.countUpAndGetSequence(board.getCategory());

        Board cloneBoard = board.toBuilder()
                .defaultSequence(defaultSequence)
                .categorySequence(categorySequence)
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
    public BoardListResponse boardListView(String category, Pageable pageable) {
        Page<BoardListViewReadModel> boardListViewReadModelsList;

        if(category == null) {
            boardListViewReadModelsList = boardRepository.findAllBy(pageable);
        } else {
            boardListViewReadModelsList = boardRepository.findByCategory(category, pageable);
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
                                            .sequence(category == null ? board.defaultSequence() : board.categorySequence())
                                            .category(board.category())
                                            .title(board.title())
                                            .username(board.username())
                                            .nickname(board.nickname())
                                            .viewCount(boardView.getViewCount())
                                            .commentCount(boardView.getCommentAndReplyCount())
                                            .createdAt(board.createdAt())
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
                        .category(board.getCategory())
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
}
