package com.self.blog.board.elasticsearch.repository;

import com.self.blog.board.elasticsearch.entity.BoardEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BoardElasticsearchRepository extends ElasticsearchRepository<BoardEntity, String> {
    List<BoardEntity> findTop8ByOrderByCreatedAtDesc();
    List<BoardEntity> findByIdIn(List<String> id);
}
