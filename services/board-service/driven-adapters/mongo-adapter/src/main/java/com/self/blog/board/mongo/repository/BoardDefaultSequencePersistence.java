package com.self.blog.board.mongo.repository;

import com.self.blog.board.application.repository.BoardDefaultSequenceRepository;
import com.self.blog.board.mongo.entity.BoardDefaultSequenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardDefaultSequencePersistence implements BoardDefaultSequenceRepository {
    private final BoardDefaultSequenceEntityRepository repository;

    @Override
    public Long CountUpAndGetSequence() {
        BoardDefaultSequenceEntity boardDefaultSequenceEntity = repository.findFirstBy()
                .orElseGet(() -> BoardDefaultSequenceEntity.builder()
                            .allSequence(0L)
                            .build()
                );

        boardDefaultSequenceEntity.setAllSequence(boardDefaultSequenceEntity.getAllSequence() + 1);

        return repository.save(boardDefaultSequenceEntity).getAllSequence();
    }
}
