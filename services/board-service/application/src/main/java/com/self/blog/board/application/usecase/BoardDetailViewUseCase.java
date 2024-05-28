package com.self.blog.board.application.usecase;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;

public interface BoardDetailViewUseCase {
    BoardAndViewCountResponse boardDetailView(String boardId, String username, String ViewIp);
}
