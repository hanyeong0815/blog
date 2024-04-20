package com.self.music.passwordEncoder.generator;

import lombok.Builder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class ScryptGenerator {

    @Builder
    public record SCryptConfigParams(
            Integer cpuCost,
            Integer memoryCost,
            Integer parallelism,
            Integer keyLength,
            Integer saltLength
    ) {
        private static class DefaultInstanceHolder {
            private static final SCryptConfigParams DEFAULT_INSTANCE =
                    new SCryptConfigParams(null, null, null, null, null);
        }

        public SCryptConfigParams {
            if (cpuCost == null) cpuCost = 65536;
            if (memoryCost == null) memoryCost = 8;
            if (parallelism == null) parallelism = 1;
            if (keyLength == null) keyLength = 32;
            if (saltLength == null) saltLength = 16;
        }

        public static SCryptConfigParams whenYouDontKnowHowToMake() {
            return DefaultInstanceHolder.DEFAULT_INSTANCE;
        }
     }

    public SCryptPasswordEncoder generate(SCryptConfigParams params) {
        return new SCryptPasswordEncoder(
                params.cpuCost(),
                params.memoryCost(),
                params.parallelism(),
                params.keyLength(),
                params.saltLength()
        );
    }
}
