package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.MemberProfile;
import com.self.blog.rdb.entity.MemberProfileEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemberProfileEntityMapper extends DomainEntityMapper<MemberProfile, MemberProfileEntity> {
}
