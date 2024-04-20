package com.self.music.common.support.mapper;

public interface DomainEntityMapper<DOMAIN, ENTITY> {
    DOMAIN toDomain(ENTITY entity);
    ENTITY toEntity(DOMAIN domain);
}
