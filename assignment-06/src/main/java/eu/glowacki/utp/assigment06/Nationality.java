package eu.glowacki.utp.assigment06;

import java.util.*;

public enum Nationality {
    Polish(new Locale("pl", "PL")),
    Ukrainian(new Locale("ua", "UA")),
    Belorussian(new Locale("be", "BY")),
    Slovak(new Locale("sk", "SK")),
    Lithuanian(new Locale("lt", "LT")),
    Latvian(new Locale("lv", "LV")),
    British(new Locale("en", "GBR")),
    Indian(new Locale("in", "IN")),
    Chinese(new Locale("zh", "CN")),
    Vietnamese(new Locale("vi", "VN"));


    private final Locale locale;

    Nationality(Locale locale) {
        this.locale = locale;
    }

    public static Nationality random() {
        Random random = new Random();
        Nationality[] values = Nationality.values();
        return values[random.nextInt(values.length)];
    }
}
