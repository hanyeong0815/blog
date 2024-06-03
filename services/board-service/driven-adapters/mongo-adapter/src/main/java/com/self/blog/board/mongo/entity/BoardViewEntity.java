package com.self.blog.board.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("view")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardViewEntity {
    @Id
    private String boardId;
    private Integer viewCount;
    private Integer commentAndReplyCount;
}
