package com.example.demo;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
    public static String removeAccent(String s) {
        if (s == null)
            return null;
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("")
                .replace('đ', 'd').replace('Đ', 'D')
                .toLowerCase(); // Đưa về chữ thường để tìm kiếm chính xác hơn
    }
}
