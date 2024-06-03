package com.self.blog.board.application.service;

import com.self.blog.board.application.aop.ViewCountUp;
import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.exception.CategoryErrorCode;
import com.self.blog.board.application.mapper.BoardMapper;
import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.application.repository.BoardViewRepository;
import com.self.blog.board.application.repository.CategoryRepository;
import com.self.blog.board.application.usecase.BoardDetailViewUseCase;
import com.self.blog.board.application.usecase.BoardListViewUseCase;
import com.self.blog.board.application.usecase.BoardSaveUseCase;
import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.application.usecase.data.BoardListView.BoardListViewResponse;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardView;
import com.self.blog.board.domain.Category;
import com.self.blog.board.readmodels.BoardReadModels.BoardListViewReadModels;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService implements
        BoardSaveUseCase,
        BoardDetailViewUseCase,
        BoardListViewUseCase
{
    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;
    private final CategoryRepository categoryRepository;

    private final BoardMapper boardMapper;

    @Override
    public BoardAndViewCountResponse boardSave(String category, Board board) {
        Category findCategory = categoryRepository.findByCategory(category)
                .orElseThrow(CategoryErrorCode.CATEGORY_NOT_FOUND::defaultException);
        board.setCategoryId(findCategory.getId());

        Board savedBoard = boardRepository.save(board);

        BoardView boardView = BoardView.builder()
                .boardId(savedBoard.getId())
                .viewCount(0)
                .commentAndReplyCount(0)
                .build();
        BoardView savedBoardView = boardViewRepository.save(boardView);

        return boardMapper.from(savedBoard, savedBoardView, findCategory.getCategory());
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
        Category category = categoryRepository.findById(board.getCategoryId())
                .orElseThrow(
                        CategoryErrorCode.CATEGORY_NOT_FOUND::defaultException
                );

        return boardMapper.from(board, boardView, category.getCategory());
    }

    @Override
    public List<BoardListViewResponse> boardListView(Pageable pageable) {
        List<BoardListViewReadModels> boardListViewReadModelsList = boardRepository.findAllBy(pageable);

        if (boardListViewReadModelsList.isEmpty()) return null;

        return boardListViewReadModelsList.stream()
                .map(board -> {
                    BoardView boardView = boardViewRepository.findByBoardId(board.id())
                            .orElseThrow(BoardErrorCode.DEFAULT::defaultException);

                    return BoardListViewResponse.builder()
                            .boardId(board.id())
                            .title(board.title())
                            .username(board.username())
                            .viewCount(boardView.getViewCount())
                            .commentCount(boardView.getCommentAndReplyCount())
                            .createdAt(board.createdAt())
                            .build();
                }).toList();
    }
}
