package com.self.blog.board.domain;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {
    public String id;
    public String username;
    public String title;
    public String content;
    public Instant createdAt;
    public List<Comment> comments;
}
