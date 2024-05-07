package com.self.blog.member.web.service;

import com.self.blog.member.application.usecase.PasswordUpdateUseCase;
import com.self.blog.member.web.dto.PasswordUpdateDto.PasswordUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordUpdateProxyService {
    private final PasswordUpdateUseCase passwordUpdateUseCase;

    public boolean passwordUpdate(PasswordUpdateRequestDto dto) {
        return passwordUpdateUseCase.updatePassword(dto.username(), dto.password());
    }
}
