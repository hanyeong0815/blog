package com.self.blog.board.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("domain")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DomainEntity {
    @Id
    private String id;
    private String domain;
    private Long sequence;
    private Integer level;
}
