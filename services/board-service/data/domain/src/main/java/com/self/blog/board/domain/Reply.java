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
public class Reply {
    private String id;
    private String username;
    private String content;
    private Instant createdAt;
    private List<Reply> replies;
    private boolean isDeleted;
}
