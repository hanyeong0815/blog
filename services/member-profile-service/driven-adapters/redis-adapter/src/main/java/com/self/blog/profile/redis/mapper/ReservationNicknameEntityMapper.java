package com.self.blog.profile.redis.mapper;

import com.self.blog.common.support.mapper.DomainEntityMapper;
import com.self.blog.profile.domain.ReservationNickname;
import com.self.blog.profile.redis.entity.ReservationNicknameEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ReservationNicknameEntityMapper extends DomainEntityMapper<ReservationNickname, ReservationNicknameEntity> {
}
