package com.self.blog.profile.internal.mapper;

import com.self.blog.profile.domain.MemberProfile;
import com.self.blog.profile.domain.type.GenderType;
import com.self.blog.profile.lib.MemberProfileDetailResponse;
import com.self.blog.profile.lib.MemberProfileSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;

@Mapper
public interface MemberProfileDtoMapper {
    MemberProfile from(MemberProfileSaveRequest memberProfileSaveRequest);
    MemberProfileDetailResponse from(MemberProfile memberProfile);

    @ValueMapping(target = MappingConstants.NULL, source = MappingConstants.ANY_REMAINING)
    GenderType toNativeEnum(com.self.blog.profile.lib.GenderType genderType);

    com.self.blog.profile.lib.GenderType toRequestEnum(GenderType genderType);


}
