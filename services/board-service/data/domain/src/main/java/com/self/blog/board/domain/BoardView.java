package com.self.blog.board.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BoardView {
    private String boardId;
    private Integer viewCount;
    private Integer commentAndReplyCount;
}
