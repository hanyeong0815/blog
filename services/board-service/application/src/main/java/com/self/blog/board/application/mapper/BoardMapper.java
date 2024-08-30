package com.self.blog.board.application.mapper;

import com.self.blog.board.application.usecase.data.BoardAndViewCount.BoardAndViewCountResponse;
import com.self.blog.board.application.usecase.data.BoardUpdateDto.BoardFindForUpdateResponse;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.BoardView;
import com.self.blog.board.domain.Comment;
import com.self.blog.board.readmodels.BoardReadModels.BoardFindForUpdateReadModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Mappings({
            @Mapping(target="commentCount", source = "boardView.commentAndReplyCount")
    })
    BoardAndViewCountResponse from(Board board, BoardView boardView, List<Comment> comments);
    BoardFindForUpdateResponse from(BoardFindForUpdateReadModel readModel);
}
