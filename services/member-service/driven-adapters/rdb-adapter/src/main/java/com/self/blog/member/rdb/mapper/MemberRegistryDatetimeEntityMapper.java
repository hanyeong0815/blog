package com.self.blog.member.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.MemberRegistryDatetime;
import com.self.blog.member.rdb.entity.MemberRegistryDatetimeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemberRegistryDatetimeEntityMapper extends DomainEntityMapper<MemberRegistryDatetime, MemberRegistryDatetimeEntity> {
}
