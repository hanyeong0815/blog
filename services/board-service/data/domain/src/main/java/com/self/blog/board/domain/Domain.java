package com.self.blog.board.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Domain {
    private String id;
    private String domain;
    private Long sequence;
    private Integer level;
}
