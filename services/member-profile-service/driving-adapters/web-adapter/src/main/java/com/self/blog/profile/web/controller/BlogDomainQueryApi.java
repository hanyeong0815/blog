package com.self.blog.profile.web.controller;

import com.self.blog.profile.domain.BlogDomain;
import com.self.blog.profile.web.service.FindDomainProxyService;
import com.self.blog.profile.web.service.IsPresentDomainProxyService;
import com.self.blog.profile.web.service.ValidateDomainProxyService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("domain")
@RequiredArgsConstructor
public class BlogDomainQueryApi {
    private final FindDomainProxyService findDomainProxyService;
    private final ValidateDomainProxyService validateDomainProxyService;
    private final IsPresentDomainProxyService isPresentDomainProxyService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("{username}")
    public BlogDomain findDomain(@PathVariable String username) {
        return findDomainProxyService.findDomain(username);
    }

    @GetMapping("validate/{domain}/{username}")
    public boolean validateDomain(@PathVariable String domain, @PathVariable String username) {
        return validateDomainProxyService.validateDomain(username, domain);
    }

    @GetMapping("present/{domain}")
    public boolean presentDomain(@PathVariable @NotBlank String domain) {
        return isPresentDomainProxyService.isPresentDomain(domain);
    }
}
