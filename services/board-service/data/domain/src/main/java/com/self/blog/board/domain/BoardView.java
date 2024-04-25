package com.self.blog.board.domain;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BoardView {
    public String boardId;
    public Integer viewCount;
}
