package com.self.blog.board.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ViewCountValidation {
    private String boardId;
    private String username;
    private String viewIp;
    private Long ttl;
}
