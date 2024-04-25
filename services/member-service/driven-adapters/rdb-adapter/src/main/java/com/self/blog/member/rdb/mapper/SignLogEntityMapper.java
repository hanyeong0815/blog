package com.self.blog.member.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.SignLog;
import com.self.blog.member.rdb.entity.SignLogEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SignLogEntityMapper extends DomainEntityMapper<SignLog, SignLogEntity> {
}
