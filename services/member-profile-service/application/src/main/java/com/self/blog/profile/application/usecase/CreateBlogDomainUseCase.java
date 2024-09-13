package com.self.blog.profile.application.usecase;

import com.self.blog.profile.domain.BlogDomain;

public interface CreateBlogDomainUseCase {
    BlogDomain createBlogDomain(String username, String domain);
}
