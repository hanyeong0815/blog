package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.AccountRegistryDatetime;
import com.self.blog.rdb.entity.AccountRegistryDatetimeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AccountRegistryDatetimeEntityMapper extends DomainEntityMapper<AccountRegistryDatetime, AccountRegistryDatetimeEntity> {
}
