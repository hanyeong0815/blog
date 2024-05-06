package com.self.blog.board.mongo.repository;

import com.self.blog.board.mongo.entity.ReplyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReplyMongoRepository extends MongoRepository<ReplyEntity, String> {
}
