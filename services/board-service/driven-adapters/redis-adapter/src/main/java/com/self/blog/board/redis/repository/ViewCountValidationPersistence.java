package com.self.blog.board.redis.repository;

import com.self.blog.board.application.repository.ViewCountValidationRepository;
import com.self.blog.board.domain.ViewCountValidation;
import com.self.blog.board.redis.entity.ViewCountValidationEntity;
import com.self.blog.board.redis.mapper.ViewCountValidationEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ViewCountValidationPersistence implements ViewCountValidationRepository {
    private final ViewCountValidationRedisRepository repository;
    private final ViewCountValidationEntityMapper mapper;

    @Override
    public ViewCountValidation save(ViewCountValidation viewCountValidation) {
        ViewCountValidationEntity viewCountValidationEntity = mapper.toEntity(viewCountValidation);
        return mapper.toDomain(
                repository.save(viewCountValidationEntity)
        );
    }

    @Override
    public boolean existsByIdAndUsername(String boardId, String username) {
        return repository.existsByBoardIdAndUsername(boardId, username);
    }
}
