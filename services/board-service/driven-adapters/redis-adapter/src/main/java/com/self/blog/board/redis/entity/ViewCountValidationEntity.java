package com.self.blog.board.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Builder
@RedisHash(value = "view_count_validation")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ViewCountValidationEntity {
    @Id
    @Indexed
    public String boardId;
    @Indexed
    public String username;
    @Indexed
    public String viewIp;
    @TimeToLive(unit = TimeUnit.DAYS)
    public Long ttl;
}
