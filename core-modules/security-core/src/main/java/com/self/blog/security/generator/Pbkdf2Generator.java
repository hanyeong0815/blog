package com.self.music.passwordEncoder.generator;

import com.self.music.passwordEncoder.type.Pbkdf2AlgorithmWithHmacOf;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class Pbkdf2Generator {
    public record Pbkdf2ConfigParams(
            String secret,
            Integer costFactor,
            Integer saltLength,
            Pbkdf2AlgorithmWithHmacOf algorithm
    ) {
        private static class DefaultInstanceHolder {
            private static final Pbkdf2ConfigParams DEFAULT_INSTANCE =
                    new Pbkdf2ConfigParams(null, null, null, null);
        }

        public Pbkdf2ConfigParams {
            if (secret == null) secret = "abcd1234abcd1234abcd1234abcd1234";
            if (costFactor == null) costFactor = 12;
            if (saltLength == null) saltLength = 32;
            if (algorithm == null) algorithm = Pbkdf2AlgorithmWithHmacOf.SHA256;
        }

        public static Pbkdf2ConfigParams whenYouDontKnowHowToMake() {
            return DefaultInstanceHolder.DEFAULT_INSTANCE;
        }
     }

    public Pbkdf2PasswordEncoder generate(Pbkdf2ConfigParams params) {
        return new Pbkdf2PasswordEncoder(
                params.secret(),
                params.costFactor(),
                params.saltLength,
                params.algorithm().compatibility());
    }
}