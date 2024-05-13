package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.Category;
import com.self.blog.board.mongo.entity.CategoryEntity;
import com.self.blog.board.mongo.support.OneToOneEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryEntityMapper extends OneToOneEntityMapper<Category, CategoryEntity> {
}
