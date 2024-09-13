package com.self.blog.profile.application.usecase;

import com.self.blog.profile.domain.BlogDomain;

public interface BlogDomainFindByProfileIdUseCase {
    BlogDomain findBlogDomainByProfileId(String username);
}
