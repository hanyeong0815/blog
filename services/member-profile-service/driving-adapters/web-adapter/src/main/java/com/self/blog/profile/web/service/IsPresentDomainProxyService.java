package com.self.blog.profile.web.service;

import com.self.blog.profile.application.usecase.IsPresentDomainUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IsPresentDomainProxyService {
    private final IsPresentDomainUseCase isPresentDomainUseCase;

    public boolean isPresentDomain(String domain) {
        return isPresentDomainUseCase.presentDomain(domain);
    }
}
