package com.self.blog.board.application.usecase;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.domain.Board;
import org.springframework.web.multipart.MultipartFile;

public interface BoardSaveUseCase {
    BoardAndViewCountResponse boardSave(Board board, int ogNumber, MultipartFile ogFile);
}
