package com.self.blog.board.domain;

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
public class ReplyOfReply {
    public String id;
    public String replyId;
    public String username;
    public String content;
    public Instant createdAt;
    public List<ReplyOfReply> replies;
}
