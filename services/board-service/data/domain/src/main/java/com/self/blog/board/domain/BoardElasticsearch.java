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
public class BoardElasticsearch {
    private String id;

    private String boardDomain;

    private String category;

    private String username;

    private String nickname;

    private String title;

    private String content;

    private Instant createdAt;

    private Boolean deleted;
}
