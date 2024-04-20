package com.self.blog.web.mapper;

import com.self.blog.domain.Member;
import com.self.blog.domain.type.MemberStatus;
import com.self.blog.web.dto.MemberSignupDto.MemberSignupRequestDto;
import com.self.blog.web.dto.MemberSignupDto.MemberSignupResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface MemberDtoMapper {
    Member from(MemberSignupRequestDto dto, MemberStatus status);
    MemberSignupResponseDto from(Member member);
}
