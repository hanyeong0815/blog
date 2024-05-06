package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.exception.BoardErrorCode;
import com.self.blog.board.application.repository.BoardRepository;
import com.self.blog.board.domain.Board;
import com.self.blog.board.domain.Reply;
import com.self.blog.board.mongo.entity.BoardEntity;
import com.self.blog.board.mongo.entity.ReplyEntity;
import com.self.blog.board.mongo.mapper.BoardEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardPersistence implements BoardRepository {
    private final BoardMongoRepository repository;
    private final ReplyMongoRepository replyMongoRepository;
    private final BoardEntityMapper mapper;

    @Override
    public Board save(Board board) {
        BoardEntity boardEntity = mapper.toEntity(board);
        return mapper.toDomain(
                repository.save(boardEntity)
        );
    }

    @Override
    public Optional<Board> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Board> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public Reply saveReply(String boardId, Reply reply) {
        BoardEntity boardEntity = repository.findById(boardId)
                .orElseThrow(BoardErrorCode.BOARD_NOT_FOUND::defaultException);

        ReplyEntity replyEntity = mapper.replyToEntity(reply, new ArrayList<>());

        if (boardEntity.replies == null) {
            boardEntity.replies = new ArrayList<>();
        }

        replyEntity.id = STR."\{boardEntity.id}_\{boardEntity.replies.size()}";
        boardEntity.replies.addLast(replyEntity);
        BoardEntity savedBoardEntity = repository.save(boardEntity);
        replyMongoRepository.save(replyEntity);

        return mapper.replyToDomain(
                savedBoardEntity.replies
                        .getLast()
        );
    }
}
