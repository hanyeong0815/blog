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
    private String domain;
    private Long defaultSequence;
    private Long categorySequence;
    @Indexed
    private String username;
    @Indexed
    private String nickname;
    @Indexed
    private String title;
    @Indexed
    private String content;
    @Indexed
    private Instant createdAt;
    @DBRef
    @Indexed
    private List<CommentEntity> comments;
    @Indexed
    private Boolean deleted;
}
