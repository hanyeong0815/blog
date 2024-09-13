package com.self.blog.profile.application.aop;

import com.self.blog.profile.application.exception.DomainErrorCode;
import com.self.blog.profile.application.repository.BlogDomainRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Component
@Aspect
@RequiredArgsConstructor
public class BlogDomainAspect {
    private final BlogDomainRepository blogDomainRepository;

    @Before(value = "@annotation(CreateDomainValidationAspect) && args(username, domain)", argNames = "username,domain")
    public void createDomainValidate(String username, String domain) {
        boolean hasDomain = blogDomainRepository.existsByDomain(domain);

        validate(
                !hasDomain,
                DomainErrorCode.DOMAIN_ALREADY_USED
        );
    }
}
