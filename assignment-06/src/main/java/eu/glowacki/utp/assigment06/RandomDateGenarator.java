package eu.glowacki.utp.assigment06;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateGenarator {

    private final long startMillis;
    private final long endMillis;

    public RandomDateGenarator(LocalDate start, LocalDate end) {
        startMillis = start.atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        endMillis = end.atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    public LocalDate nextDate() {
        long random = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        return Instant.ofEpochMilli(random)
                .atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
