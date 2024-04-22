package com.self.blog.web.mapper;

import com.self.blog.domain.Member;
import com.self.blog.domain.type.GenderType;
import com.self.blog.domain.type.MemberStatus;
import com.self.blog.profile.lib.MemberProfileSaveRequest;
import com.self.blog.web.dto.MemberSignupDto.MemberSignupRequestDto;
import org.mapstruct.*;

@Mapper
public interface MemberDtoMapper {
    Member from(MemberSignupRequestDto dto, MemberStatus status);

    @Mappings({
            @Mapping(target = "memberId", source = "member.id"),
            @Mapping(target = "username", source = "member.username")
    })
    MemberProfileSaveRequest from(Member member, MemberSignupRequestDto dto);

    @ValueMapping(target = MappingConstants.NULL, source = MappingConstants.ANY_REMAINING)
    GenderType toNativeEnum(com.self.blog.profile.lib.GenderType genderType);

    @ValueMapping(target = MappingConstants.NULL, source = MappingConstants.ANY_REMAINING)
    com.self.blog.profile.lib.GenderType toRequestEnum(GenderType genderType);
}
