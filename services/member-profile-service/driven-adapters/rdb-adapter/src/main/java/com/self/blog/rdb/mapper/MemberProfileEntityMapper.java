package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.MemberProfile;
import com.self.blog.rdb.entity.MemberProfileEntity;
import com.self.blog.rdb.projection.MemberProfileProjections.MemberProfileDetailViewProjection;
import com.self.blog.rdb.projection.MemberProfileProjections.MemberProfileNicknameProjection;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileDetailView;
import com.self.blog.read_model.MemberProfileReadModels.MemberProfileNickname;
import org.mapstruct.Mapper;

@Mapper
public interface MemberProfileEntityMapper extends DomainEntityMapper<MemberProfile, MemberProfileEntity> {
    MemberProfileNickname from(MemberProfileNicknameProjection memberProfileNicknameProjection);
    MemberProfileDetailView from(MemberProfileDetailViewProjection memberProfileDetailViewProjection);
}
