package com.self.blog.board.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryEntity {
    @Id
    public String id;
    public String category;
    public Integer level;
}
