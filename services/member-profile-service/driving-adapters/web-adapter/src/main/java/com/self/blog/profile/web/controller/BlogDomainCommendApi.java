package com.self.blog.profile.web.controller;

import com.self.blog.profile.domain.BlogDomain;
import com.self.blog.profile.web.dto.CreateDomainDto.CreateDomainRequestDto;
import com.self.blog.profile.web.service.CreateDomainProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("domain")
@RequiredArgsConstructor
public class BlogDomainCommendApi {
    private final CreateDomainProxyService createDomainProxyService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("")
    public BlogDomain createDomain(@RequestBody CreateDomainRequestDto dto) {
        return createDomainProxyService.createDomain(dto);
    }
}
