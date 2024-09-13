package com.self.blog.board.domain;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BoardActionLog {
    private String boardId;
    private String action;
    private Instant timestamp;
}
