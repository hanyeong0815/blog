package com.self.music.common.utils.random;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64.Encoder;

@Component
@RequiredArgsConstructor
public class MusicStrongStringRandom implements StrongStringRandom {

    /**
     * Base 64 Encoder
     */
    private final Encoder encoder;
    private final SecureRandom random;

    @Override
    public String nextString() {
        return nextString(32);
    }

    @Override
    public String nextString(int length) {
        // TODO 하이픈(-) 제외하면 잘린 길이가 length를 보장하지 못함.

        return nextStringWithBytes(length)
                // .replaceAll("-", "")
                .substring(0, length);
    }

    @Override
    public String nextStringWithBytes(int byteSize) {
        // [1] fill bytes with random.
        byte[] bytes = new byte[byteSize];
        random.nextBytes(bytes);

        // [2] encode to string
        return encoder.encodeToString(bytes); // Base 64로 인코딩 하고 나면 길이가 byteSize 이상.
    }
}
