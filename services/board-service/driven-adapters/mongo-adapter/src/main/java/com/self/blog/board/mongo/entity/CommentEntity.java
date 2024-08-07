package com.self.blog.board.mongo.entity;

import com.self.blog.board.mongo.entity.components.ReplyEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("comment")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentEntity {
    @Id
    private String id;
    @Indexed
    private String username;
    @Indexed
    private String content;
    @Indexed
    private Instant createdAt;
    @Indexed
    private List<ReplyEntity> replies;
    private boolean isDeleted;
}
