package com.self.blog.board.application.utils;

import com.self.blog.board.application.exception.BoardErrorCode;

public class OgFactory {
    private static final String NUMBER_1 = "1.png";
    private static final String NUMBER_2 = "2.png";
    private static final String NUMBER_3 = "3.png";
    private static final String NUMBER_4 = "4.png";

    private OgFactory() {}

    private static class LazyHolder {
        private static final OgFactory IT = new OgFactory();
    }

    public static OgFactory getInstance() {
        return LazyHolder.IT;
    }

    public String getOgFileName(int ogNumber) {
        return switch (ogNumber) {
            case 1 -> NUMBER_1;
            case 2 -> NUMBER_2;
            case 3 -> NUMBER_3;
            case 4 -> NUMBER_4;
            default -> throw BoardErrorCode.BOARD_NOT_FOUND.defaultException();
        };
    }
}
