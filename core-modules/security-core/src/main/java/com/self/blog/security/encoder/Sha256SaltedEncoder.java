package com.self.music.passwordEncoder.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Sha256SaltedEncoder implements PasswordEncoder {

        private final String salt;
        private static final Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

        Sha256SaltedEncoder(String salt) {
            if (salt.length() < 8) {
                throw new IllegalArgumentException("Salt의 길이는 최소 8글자 이상");
            }

            this.salt = salt;
        }

        @Override
        public String encode(CharSequence rawPassword) {
            String saltedString = rawPassword + salt;

            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(saltedString.getBytes(StandardCharsets.UTF_8));
                return base64Encoder.encodeToString(hash);

                // if: requires hex
//                StringBuilder hexString = new StringBuilder();
//                for (byte b : hash) {
//                    String hex = Integer.toHexString(b);
//                    if (hex.length() == 1) {
//                        hexString.append('0');
//                    }
//                    hexString.append(hex);
//                }
//                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA-256 알고리즘이 지원되지 않습니다.", e);
            }
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return encode(rawPassword).equals(encodedPassword);
        }
    }