package com.self.blog.board.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MemberDomain {
    private String id;
    private String domain;
    private List<Board> boardEntityList;
}
