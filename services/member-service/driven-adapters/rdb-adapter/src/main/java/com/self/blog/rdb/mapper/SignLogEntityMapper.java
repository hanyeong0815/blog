package com.self.blog.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.domain.SignLog;
import com.self.blog.rdb.entity.SignLogEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SignLogEntityMapper extends DomainEntityMapper<SignLog, SignLogEntity> {
}
