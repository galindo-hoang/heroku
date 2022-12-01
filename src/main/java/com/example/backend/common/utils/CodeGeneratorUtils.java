package com.example.backend.common.utils;

import java.util.Random;

public class CodeGeneratorUtils {
    public static int CODE_SIZE = 6;
    public static String invoke(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 90; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i >= 48 && i <= 57) || (i >= 65 && i <= 90))
                .limit(CODE_SIZE)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
