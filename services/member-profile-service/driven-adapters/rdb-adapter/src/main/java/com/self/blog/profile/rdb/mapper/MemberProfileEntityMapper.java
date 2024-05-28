package com.self.blog.profile.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.profile.domain.MemberProfile;
import com.self.blog.profile.rdb.entity.MemberProfileEntity;
import com.self.blog.profile.rdb.projection.MemberProfileProjections.MemberProfileDetailViewProjection;
import com.self.blog.profile.rdb.projection.MemberProfileProjections.MemberProfileNicknameProjection;
import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.profile.read_model.MemberProfileReadModels.MemberProfileNickname;
import org.mapstruct.Mapper;

@Mapper
public interface MemberProfileEntityMapper extends DomainEntityMapper<MemberProfile, MemberProfileEntity> {
    MemberProfileNickname from(MemberProfileNicknameProjection memberProfileNicknameProjection);
    MemberProfileDetailView from(MemberProfileDetailViewProjection memberProfileDetailViewProjection);
}
