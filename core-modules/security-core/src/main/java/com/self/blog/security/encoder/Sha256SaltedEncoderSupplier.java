package com.self.music.passwordEncoder.encoder;

import org.springframework.stereotype.Component;

@Component
public final class Sha256SaltedEncoderSupplier {
    /**
     * 사용자 요청 때마다 Sha 256 인코더 생성
     * @param fixedSalt 사용자마다 가지는 고정 Salt
     */
    public Sha256SaltedEncoder getEncoder(String fixedSalt) {
        return new Sha256SaltedEncoder(fixedSalt);
    }
}