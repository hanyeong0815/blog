package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.MemberStaticSalt;
import com.self.blog.rdb.entity.MemberStaticSaltEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemberStaticSaltEntityMapper extends DomainEntityMapper<MemberStaticSalt, MemberStaticSaltEntity> {
}
