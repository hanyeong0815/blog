package com.self.blog.board.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("sequence")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDefaultSequenceEntity {
    @Id
    private String id;
    @Indexed
    private Long allSequence;
}
