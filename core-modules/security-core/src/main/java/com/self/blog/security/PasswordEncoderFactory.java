package com.self.music.passwordEncoder;

import com.self.music.passwordEncoder.encoder.PrefixedBcryptEncoder;
import com.self.music.passwordEncoder.generator.Pbkdf2Generator;
import com.self.music.passwordEncoder.generator.Pbkdf2Generator.Pbkdf2ConfigParams;
import com.self.music.passwordEncoder.generator.ScryptGenerator;
import com.self.music.passwordEncoder.generator.ScryptGenerator.SCryptConfigParams;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>패스워드 인코더 팩토리</h1>
 * <p>동시성을 보장하며, 패스워드 인코더 유형에 따라 동일한 인스턴스를 제공.</p>
 */
@Component
public class PasswordEncoderFactory {

    private static class StaticFieldsHolder {
        private static final ScryptGenerator SCRYPT_GENERATOR = new ScryptGenerator();
        private static final Pbkdf2Generator PBKDF2_GENERATOR = new Pbkdf2Generator();
        private static final Map<Integer, BCryptPasswordEncoder> BCRYPT_MAP = new ConcurrentHashMap<>();
        private static final Map<String, SCryptPasswordEncoder> SCRYPT_MAP = new ConcurrentHashMap<>();
        private static final Map<String, Pbkdf2PasswordEncoder> PBKDF2_MAP = new ConcurrentHashMap<>();
    }

    /**
     *
     */
    public BCryptPasswordEncoder createBcrypt(int strength) {
        return StaticFieldsHolder.BCRYPT_MAP
                .computeIfAbsent(strength, PrefixedBcryptEncoder::new);
    }

    public SCryptPasswordEncoder createScrypt(String encoderId, SCryptConfigParams params) {
        return StaticFieldsHolder.SCRYPT_MAP
                .computeIfAbsent(encoderId, (__) -> StaticFieldsHolder.SCRYPT_GENERATOR.generate(params));
    }

    public Pbkdf2PasswordEncoder createPbkdf2(String encoderId, Pbkdf2ConfigParams params) {
        return StaticFieldsHolder.PBKDF2_MAP
                .computeIfAbsent(encoderId, (__) -> StaticFieldsHolder.PBKDF2_GENERATOR.generate(params));
    }

    /**
     * 생성해 둔 SCRYPT 인코더를 찾거나, 지정한 예외를 던짐.
     */
    public SCryptPasswordEncoder findScryptOrElseThrow(String encoderId, Throwable throwable) throws Throwable {
        return Optional.of(StaticFieldsHolder.SCRYPT_MAP.get(encoderId))
                .orElseThrow(() -> throwable);
    }

    /**
     * 생성해 둔 SCRYPT 인코더를 찾거나 기본 예외를 던짐.
     */
    public SCryptPasswordEncoder findScryptOrElseThrow(String encoderId) throws IllegalArgumentException {
        return Optional.of(StaticFieldsHolder.SCRYPT_MAP.get(encoderId))
                .orElseThrow(() -> new IllegalArgumentException("No such Key here."));
    }

    /**
     * 생성해 둔 SCRYPT 인코더를 찾거나 기본 객체를 가져옴.
     */
    public SCryptPasswordEncoder findScryptOrDefaultObject(String encoderId) {
        SCryptConfigParams params = SCryptConfigParams.whenYouDontKnowHowToMake();
        return StaticFieldsHolder.SCRYPT_MAP
                .computeIfAbsent(encoderId, (__) -> StaticFieldsHolder.SCRYPT_GENERATOR.generate(params));
    }
}
