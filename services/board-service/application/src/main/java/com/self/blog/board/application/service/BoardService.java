package com.self.blog.board.application.service;

import com.self.blog.board.application.aop.ViewCountUp;
import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.mapper.BoardMapper;
import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.repository.BoardViewRepository;
import com.self.blog.board.application.usecase.BoardDetailViewUseCase;
import com.self.blog.board.application.usecase.BoardListViewUseCase;
import com.self.blog.board.application.usecase.BoardSaveUseCase;
import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListResponse;
import com.self.blog.board.application.usecase.data.BoardListViewDto.BoardListView;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardView;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModels;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService implements
        BoardSaveUseCase,
        BoardDetailViewUseCase,
        BoardListViewUseCase
{
    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;

    private final BoardMapper boardMapper;

    @Override
    public BoardAndViewCountResponse boardSave(Board board) {
        Board savedBoard = boardRepository.save(board);

        BoardView boardView = BoardView.builder()
                .boardId(savedBoard.getId())
                .viewCount(0)
                .commentAndReplyCount(0)
                .build();
        BoardView savedBoardView = boardViewRepository.save(boardView);

        return boardMapper.from(savedBoard, savedBoardView);
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

        return boardMapper.from(board, boardView);
    }

    @Override
    public BoardListResponse boardListView(String category, Pageable pageable) {
        Page<BoardListViewReadModels> boardListViewReadModelsList;

        if(category == null) {
            boardListViewReadModelsList = boardRepository.findAllBy(pageable);
        } else {
            boardListViewReadModelsList = boardRepository.findByCategory(category, pageable);
        }

        if (boardListViewReadModelsList.isEmpty()) return null;

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
                                            .category(board.category())
                                            .title(board.title())
                                            .username(board.username())
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
}
