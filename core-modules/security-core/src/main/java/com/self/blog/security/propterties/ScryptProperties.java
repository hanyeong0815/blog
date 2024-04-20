package com.self.music.passwordEncoder.propterties;

public record ScryptProperties(
        Integer cpuCost,
        Integer memoryBlockSize,
        Integer parallelism,
        Integer keyLength,
        Integer saltLength
) {
    public ScryptProperties {
        if (cpuCost == null) cpuCost = 65536;
        if (memoryBlockSize == null) memoryBlockSize = 8;
        if (parallelism == null) parallelism = 1;
        if (keyLength == null) keyLength = 32;
        if (saltLength == null) saltLength = 16;
    }
}
