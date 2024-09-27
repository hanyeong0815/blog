package com.self.blog.profile.application.service;

import com.self.blog.profile.application.aop.CreateDomainValidationAspect;
import com.self.blog.profile.application.exception.DomainErrorCode;
import com.self.blog.profile.application.exception.MemberProfileErrorCode;
import com.self.blog.profile.application.repository.BlogDomainRepository;
import com.self.blog.profile.application.repository.MemberProfileRepository;
import com.self.blog.profile.application.usecase.BlogDomainFindByProfileIdUseCase;
import com.self.blog.profile.application.usecase.CreateBlogDomainUseCase;
import com.self.blog.profile.application.usecase.IsPresentDomainUseCase;
import com.self.blog.profile.application.usecase.ValidateDomainUseCase;
import com.self.blog.profile.domain.BlogDomain;
import com.self.blog.profile.domain.type.BlogDomainStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.self.blog.common.utils.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public class BlogDomainService implements
        CreateBlogDomainUseCase,
        BlogDomainFindByProfileIdUseCase,
        ValidateDomainUseCase,
        IsPresentDomainUseCase
{
    private final BlogDomainRepository blogDomainRepository;
    private final MemberProfileRepository memberProfileRepository;

    @CreateDomainValidationAspect
    @Override
    public BlogDomain createBlogDomain(String username, String domain) {
        Long profileId = memberProfileRepository.findIdByUsername(username)
                .orElseThrow(
                        MemberProfileErrorCode.NO_SUCH_USER::defaultException
                ).id();

        boolean hasDomainByProfileId = blogDomainRepository.existsByProfileId(profileId);

        validate(
                !hasDomainByProfileId,
                DomainErrorCode.DOMAIN_ALREADY_HAVE
        );

        BlogDomain blogDomain = BlogDomain.builder()
                .profileId(profileId)
                .domain(domain)
                .status(BlogDomainStatus.ACTIVE)
                .build();

        return blogDomainRepository.save(blogDomain);
    }

    @Override
    public BlogDomain findBlogDomainByProfileId(String username) {
        Long profileId = memberProfileRepository.findIdByUsername(username)
                .orElseThrow(
                        MemberProfileErrorCode.NO_SUCH_USER::defaultException
                ).id();

        return blogDomainRepository.findByProfileId(profileId)
                .orElse(null);
    }

    @Override
    public boolean validateDomain(String username, String domain) {
        Long profileId = memberProfileRepository.findIdByUsername(username)
                .orElseThrow(
                        MemberProfileErrorCode.NO_SUCH_USER::defaultException
                ).id();

        BlogDomain blogDomain = blogDomainRepository.findByProfileId(profileId)
                .orElseThrow(
                        DomainErrorCode.DOMAIN_NOT_FOUND::defaultException
                );

        return Objects.equals(blogDomain.getDomain(), domain);
    }

    @Override
    public boolean presentDomain(String domain) {
        return !blogDomainRepository.existsByDomain(domain);
    }
}
