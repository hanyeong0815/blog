package com.self.blog.profile.web.service;

import com.self.blog.profile.application.usecase.ValidateDomainUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateDomainProxyService {
    private final ValidateDomainUseCase validateDomainUseCase;

    public boolean validateDomain(String username, String domain) {
        return validateDomainUseCase.validateDomain(username, domain);
    }
}
