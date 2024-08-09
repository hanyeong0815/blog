package com.self.blog.board.domain;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {
    private String id;
    private String category;
    private String username;
    private String title;
    private String content;
    private Instant createdAt;
    private List<Comment> comments;
}
