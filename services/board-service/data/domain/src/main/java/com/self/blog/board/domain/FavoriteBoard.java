package com.self.blog.board.domain;

import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FavoriteBoard {
    private String id;
    private String boardId;
    private String username;
    private Instant createdAt;
}
