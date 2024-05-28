package com.self.blog.board.web.mapper;

import com.self.blog.board.domain.Category;
import com.self.blog.board.web.dto.CategoryDto.CategorySaveRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryDtoMapper {
    Category from(CategorySaveRequestDto dto);
}
