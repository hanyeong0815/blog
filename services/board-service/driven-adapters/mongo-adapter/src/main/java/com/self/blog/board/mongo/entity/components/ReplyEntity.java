package com.self.blog.board.mongo.entity.components;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyEntity {
    private String id;
    private String username;
    private String nickname;
    private String content;
    private Instant createdAt;
    private List<ReplyEntity> replies;
    private boolean isDeleted;
}
