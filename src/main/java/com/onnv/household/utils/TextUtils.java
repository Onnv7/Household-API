package com.onnv.household.utils;

import org.springframework.stereotype.Component;

@Component
public class TextUtils {
    public String clearSpace(String input) {
        return input.replace(" ", "");
    }
    public static String replaceNonAlphanumeric(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }
}
