package com.self.blog.board.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Builder
@RedisHash(value = "view_count_validation")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ViewCountValidationEntity {
    @Id
    @Indexed
    private String boardId;
    @Indexed
    private String username;
    @Indexed
    private String viewIp;
    @TimeToLive(unit = TimeUnit.DAYS)
    private Long ttl;
}
