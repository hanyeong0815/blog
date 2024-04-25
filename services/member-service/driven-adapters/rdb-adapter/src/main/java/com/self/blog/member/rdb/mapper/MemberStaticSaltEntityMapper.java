package com.self.blog.member.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.MemberStaticSalt;
import com.self.blog.member.rdb.entity.MemberStaticSaltEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemberStaticSaltEntityMapper extends DomainEntityMapper<MemberStaticSalt, MemberStaticSaltEntity> {
}
