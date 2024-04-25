package com.self.blog.board.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardEntity {
    @Id
    public String id;
    @Indexed
    public String username;
    @Indexed
    public String title;
    @Indexed
    public String content;
    @Indexed
    public Instant createdAt;
}
