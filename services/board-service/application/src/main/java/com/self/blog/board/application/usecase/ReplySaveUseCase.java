package com.self.blog.board.application.usecase;

import com.self.blog.board.domain.Reply;

public interface ReplySaveUseCase {
    Reply saveReply(String boardId, Reply reply);
}
