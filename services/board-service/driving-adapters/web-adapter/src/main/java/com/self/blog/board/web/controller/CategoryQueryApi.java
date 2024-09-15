package com.self.blog.board.web.controller;

import com.self.blog.board.domain.Domain;
import com.self.blog.board.web.service.CategoryFindAllProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryQueryApi {
    private final CategoryFindAllProxyService categoryFindAllProxyService;

    @GetMapping("")
    public List<Domain> findAll() {
        return categoryFindAllProxyService.findAll();
    }
}
