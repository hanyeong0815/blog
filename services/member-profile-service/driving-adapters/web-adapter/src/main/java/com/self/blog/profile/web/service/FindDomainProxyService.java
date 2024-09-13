package com.self.blog.profile.web.service;

import com.self.blog.profile.application.usecase.BlogDomainFindByProfileIdUseCase;
import com.self.blog.profile.domain.BlogDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindDomainProxyService {
    private final BlogDomainFindByProfileIdUseCase blogDomainFindByProfileIdUseCase;

    public BlogDomain findDomain(String username) {
        return blogDomainFindByProfileIdUseCase.findBlogDomainByProfileId(username);
    }
}
