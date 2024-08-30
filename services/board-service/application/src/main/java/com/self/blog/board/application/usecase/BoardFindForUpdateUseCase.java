package com.self.blog.board.application.usecase;

import com.self.blog.board.application.usecase.data.BoardUpdateDto.BoardFindForUpdateResponse;

public interface BoardFindForUpdateUseCase {
    BoardFindForUpdateResponse boardFindForUpdate(String boardId, String username);
}
