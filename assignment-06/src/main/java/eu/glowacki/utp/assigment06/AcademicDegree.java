package eu.glowacki.utp.assigment06;

import java.util.Random;

public enum AcademicDegree {
    BS,
    MS,
    PHD;

    public static AcademicDegree random() {
        Random random = new Random();
        AcademicDegree[] values = AcademicDegree.values();
        return values[random.nextInt(values.length)];
    }
}
