package com.self.blog.member.redis.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.member.domain.InvitationCode;
import com.self.blog.member.redis.entity.InvitationCodeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface InvitationCodeEntityMapper extends DomainEntityMapper<InvitationCode, InvitationCodeEntity> {
}
