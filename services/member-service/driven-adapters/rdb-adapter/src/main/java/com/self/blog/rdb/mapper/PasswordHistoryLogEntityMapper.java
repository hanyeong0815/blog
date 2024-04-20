package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.PasswordHistoryLog;
import com.self.blog.rdb.entity.PasswordHistoryLogEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PasswordHistoryLogEntityMapper extends DomainEntityMapper<PasswordHistoryLog, PasswordHistoryLogEntity> {
}
