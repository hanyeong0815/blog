package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.PasswordLastUpdate;
import com.self.blog.rdb.entity.PasswordLastUpdateEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PasswordLastUpdateEntityMapper extends DomainEntityMapper<PasswordLastUpdate, PasswordLastUpdateEntity> {
}
