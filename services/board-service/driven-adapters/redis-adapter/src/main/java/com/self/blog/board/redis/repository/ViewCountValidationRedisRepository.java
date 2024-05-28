package com.self.blog.board.redis.repository;

import com.self.blog.board.redis.entity.ViewCountValidationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ViewCountValidationRedisRepository extends CrudRepository<ViewCountValidationEntity, String> {
    boolean existsByBoardIdAndUsername(String boardId, String username);
    boolean existsByBoardIdAndViewIp(String boardId, String viewIp);
}
