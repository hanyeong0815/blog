package com.self.blog.profile.web.service;

import com.self.blog.profile.application.usecase.CreateBlogDomainUseCase;
import com.self.blog.profile.domain.BlogDomain;
import com.self.blog.profile.web.dto.CreateDomainDto.CreateDomainRequestDto;
import com.self.blog.profile.web.mapper.BlogDomainDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDomainProxyService {
    private final CreateBlogDomainUseCase createBlogDomainUseCase;

    private final BlogDomainDtoMapper mapper;

    public BlogDomain createDomain(CreateDomainRequestDto dto) {
        return createBlogDomainUseCase.createBlogDomain(dto.username(), dto.domain());
    }
}
