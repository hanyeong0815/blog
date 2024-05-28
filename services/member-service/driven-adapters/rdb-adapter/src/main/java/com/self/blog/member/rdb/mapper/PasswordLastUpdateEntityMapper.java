package com.self.blog.member.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.PasswordLastUpdate;
import com.self.blog.member.rdb.entity.PasswordLastUpdateEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PasswordLastUpdateEntityMapper extends DomainEntityMapper<PasswordLastUpdate, PasswordLastUpdateEntity> {
}
