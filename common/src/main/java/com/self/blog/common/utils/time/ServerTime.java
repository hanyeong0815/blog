package com.self.music.common.utils.time;

import com.self.music.common.properties.time.ServerTimeProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
public final class ServerTime {
    public final ZoneId zoneId;

    public ServerTime(ServerTimeProperties serverTimeProperties) {
        zoneId = ZoneId.of(serverTimeProperties.timeZone());
    }

    public Instant nowInstant() {
        return this.now().toInstant();
    }

    public OffsetDateTime now() {
        return OffsetDateTime.now(zoneId);
    }

    public LocalDateTime nowLocal() {
        return now().toLocalDateTime();
    }
}
