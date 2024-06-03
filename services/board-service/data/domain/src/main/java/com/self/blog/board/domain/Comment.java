package com.self.blog.board.domain;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    private String id;
    private String username;
    private String content;
    private Instant createdAt;
    private List<Reply> replies;
    private boolean isDeleted;
}
