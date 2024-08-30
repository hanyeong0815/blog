package com.self.blog.board.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("favorite_board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavoriteBoardEntity {
    @Id
    private String id;
    @Indexed
    private String boardId;
    @Indexed
    private String username;
    @Indexed
    private Instant createdAt;
}
