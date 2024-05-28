package com.self.blog.gson.config;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class GsonSerializers {

    private GsonSerializers() {}

    // ====================================================================================================== Serializer
    public static class OffsetDateTimeSerializer implements JsonSerializer<OffsetDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        @Override
        public JsonElement serialize(OffsetDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(src));
        }
    }

    public static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(src));
        }
    }

    public static class InstantSerializer implements JsonSerializer<Instant> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        @Override
        public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(src));
        }
    }

    // ==================================================================================================== Deserializer
    public static class OffsetDateTimeDeserializer implements JsonDeserializer<OffsetDateTime> {

        private final ZoneId zoneId;

        public OffsetDateTimeDeserializer(String timeZone) {
            zoneId = ZoneId.of(timeZone);
        }

        @Override
        public OffsetDateTime deserialize(
                JsonElement json,
                Type typeOfT,
                JsonDeserializationContext context
        ) throws JsonParseException {
            String timeString = json.getAsString();
            try {
                return OffsetDateTime.parse(timeString); // FE 쪽에 이 양식을 권장
            } catch (DateTimeParseException e) {
                // 현 시점에서 오프셋 값을 얻습니다.
                ZoneOffset zoneOffset = OffsetDateTime.now(zoneId).getOffset();

                return OffsetDateTime.of(
                        LocalDateTime.parse(timeString),
                        zoneOffset
                );
            }
        }
    }

    public static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String timeString = json.getAsString();

            return LocalDateTime.parse(timeString);
        }
    }

    public static class InstantDeserializer implements JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(
                JsonElement json,
                Type typeOfT,
                JsonDeserializationContext context
        ) throws JsonParseException {
            String timeString = json.getAsString();

            try {
                return Instant.parse(timeString);
            } catch (DateTimeParseException e) {
                return Instant.from(LocalDateTime.parse(timeString));
            }
        }
    }
}
