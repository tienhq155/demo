package com.example.demo.util;

import java.text.Normalizer;

public class TextUtils {
    public static String removeAccent(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toLowerCase()
                .replace('đ', 'd').replace('Đ', 'D');

    }
}
