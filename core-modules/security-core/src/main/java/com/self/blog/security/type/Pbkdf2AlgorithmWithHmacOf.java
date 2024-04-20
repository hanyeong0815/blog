package com.self.blog.security.type;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

public enum Pbkdf2AlgorithmWithHmacOf {
    SHA1(SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1),
    SHA256(SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256),
    SHA512(SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);

    final SecretKeyFactoryAlgorithm algorithm;

    Pbkdf2AlgorithmWithHmacOf(SecretKeyFactoryAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public SecretKeyFactoryAlgorithm compatibility() {
        return this.algorithm;
    }
}
