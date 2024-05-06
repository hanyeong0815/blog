package com.self.blog.board.domain;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Getter
    public String id;
    public String username;
    public String content;
    public Instant createdAt;
    public List<Reply> replies;
}
