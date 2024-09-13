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
@EqualsAndHashCode
public class Board {
    private String id;
    private String memberDomainId;
    private Long defaultSequence;
    private Long categorySequence;
    private String boardDomain;
    private String category;
    private String username;
    private String nickname;
    private String title;
    private String content;
    private Instant createdAt;
    private List<Comment> comments;
    private Boolean deleted;
}
