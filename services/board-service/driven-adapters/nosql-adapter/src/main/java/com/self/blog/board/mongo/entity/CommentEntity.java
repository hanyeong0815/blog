package com.self.blog.board.mongo.entity;

import com.self.blog.board.mongo.entity.components.ReplyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentEntity {
    @Id
    public String id;
    @Indexed
    public String username;
    @Indexed
    public String content;
    @Indexed
    public Instant createdAt;
    @Indexed
    public List<ReplyEntity> replies;
}
