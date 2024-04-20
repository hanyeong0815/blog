package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.MemberRegistryDatetime;
import com.self.blog.rdb.entity.MemberRegistryDatetimeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MemberRegistryDatetimeEntityMapper extends DomainEntityMapper<MemberRegistryDatetime, MemberRegistryDatetimeEntity> {
}
