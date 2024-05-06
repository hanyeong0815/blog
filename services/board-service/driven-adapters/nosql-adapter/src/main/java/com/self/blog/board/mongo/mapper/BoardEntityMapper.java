package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.Reply;
import com.self.blog.board.mongo.entity.BoardEntity;
import com.self.blog.board.mongo.entity.ReplyEntity;
import com.self.blog.board.mongo.entity.components.ReplyOfReplyEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BoardEntityMapper extends OneToOneEntityMapper<Board, BoardEntity> {
    ReplyEntity replyToEntity(Reply reply, List<ReplyOfReplyEntity> replies);
    Reply replyToDomain(ReplyEntity replyEntity);
}
