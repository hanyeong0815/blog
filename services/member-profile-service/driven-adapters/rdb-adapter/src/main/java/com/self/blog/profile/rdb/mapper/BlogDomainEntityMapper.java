package com.self.blog.profile.rdb.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.profile.domain.BlogDomain;
import com.self.blog.profile.rdb.entity.BlogDomainEntity;
import org.mapstruct.Mapper;

@Mapper
public interface BlogDomainEntityMapper extends DomainEntityMapper<BlogDomain, BlogDomainEntity> {
}
