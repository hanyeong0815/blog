package com.self.blog.board.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardEntity {
    @Id
    private String id;
    @Indexed
    private String categoryId;
    @Indexed
    private String username;
    @Indexed
    private String title;
    @Indexed
    private String content;
    @Indexed
    private Instant createdAt;
    @DBRef
    @Indexed
    private List<CommentEntity> comments;
}
