package com.self.blog.board.web.mapper;

import com.self.blog.board.domain.Domain;
import com.self.blog.board.web.dto.CategoryDto.CategorySaveRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryDtoMapper {
    Domain from(CategorySaveRequestDto dto, Long sequence);
}
