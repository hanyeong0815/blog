package com.self.blog.profile.web.service;

import com.self.blog.profile.application.usecase.ValidateDomainUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateDomainProxyService {
    private final ValidateDomainUseCase validateDomainUseCase;

    public String validateDomain(String username) {
        return validateDomainUseCase.validateDomain(username);
    }
}
