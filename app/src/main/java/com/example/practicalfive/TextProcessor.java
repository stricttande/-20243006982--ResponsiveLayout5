package com.example.practicalfive;

import java.util.Locale;

public final class TextProcessor {
    private TextProcessor() {
    }

    public static String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        int start = 0;
        int end = municipality.length();
        while (start < end && Character.isWhitespace(municipality.charAt(start))) {
            start++;
        }
        while (end > start && Character.isWhitespace(municipality.charAt(end - 1))) {
            end--;
        }
        if (start == end) {
            return null;
        }

        switch (municipality.substring(start, end).toLowerCase(Locale.ROOT)) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null;
        }
    }
}
