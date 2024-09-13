package com.self.blog.board.mongo.mapper;

import com.self.blog.board.domain.MemberDomain;
import com.self.blog.board.mongo.entity.MemberDomainEntity;
import com.self.blog.common.support.mapper.DomainEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface MemberDomainEntityMapper extends DomainEntityMapper<MemberDomain, MemberDomainEntity> {
}
