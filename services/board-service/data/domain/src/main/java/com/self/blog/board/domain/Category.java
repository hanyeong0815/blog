package com.self.blog.board.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private String id;
    private String category;
    private Long sequence;
    private Integer level;
}
