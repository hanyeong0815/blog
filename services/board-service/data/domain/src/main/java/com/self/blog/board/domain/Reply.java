package com.self.blog.board.domain;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
    @Getter
    public String id;
    public String username;
    public String content;
    public Instant createdAt;
    public List<Reply> replies;
}
