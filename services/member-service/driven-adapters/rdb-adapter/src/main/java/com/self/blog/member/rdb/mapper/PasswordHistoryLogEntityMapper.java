package com.self.blog.member.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.PasswordHistoryLog;
import com.self.blog.member.rdb.entity.PasswordHistoryLogEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PasswordHistoryLogEntityMapper extends DomainEntityMapper<PasswordHistoryLog, PasswordHistoryLogEntity> {
}
