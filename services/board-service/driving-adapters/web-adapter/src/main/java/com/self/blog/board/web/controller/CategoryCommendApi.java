package com.self.blog.board.web.controller;

import com.self.blog.board.domain.Domain;
import com.self.blog.board.web.dto.CategoryDto.CategorySaveRequestDto;
import com.self.blog.board.web.service.CategorySaveProxyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryCommendApi {
    private final CategorySaveProxyService categorySaveProxyService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("")
    public Domain saveCategory(@RequestBody @Valid CategorySaveRequestDto dto) {
        return categorySaveProxyService.saveCategory(dto);
    }
}
