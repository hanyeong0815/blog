package com.self.blog.board.mongo.entity;

import com.self.blog.board.mongo.entity.components.ReplyOfReplyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("reply")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyEntity {
    @Id
    public String id;
    @Indexed
    public String username;
    @Indexed
    public String content;
    @Indexed
    public Instant createdAt;
    @Indexed
    public List<ReplyOfReplyEntity> replies;
}
