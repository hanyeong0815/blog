package com.self.blog.common.utils.random;

public interface StrongStringRandom {
    String nextString(); // 32글자, - 제외
    String nextString(int length);
    String nextStringWithBytes(int byteSize);
}
