package com.self.blog.board.mongo.entity.components;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyEntity {
    public String id;
    public String username;
    public String content;
    public Instant createdAt;
    public List<ReplyEntity> replies;
}
