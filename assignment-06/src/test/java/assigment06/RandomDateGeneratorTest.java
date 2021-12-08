package assigment06;

import eu.glowacki.utp.assigment06.RandomDateGenarator;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class RandomDateGeneratorTest {

    @Test
    public void generationTest() {

        LocalDate startDate = LocalDate.of(1970,1, 1);
        LocalDate endDate = LocalDate.now();

        RandomDateGenarator randomDate = new RandomDateGenarator(startDate, endDate);

        final int ITERATIONS = 100;

        for (int i = 0; i < ITERATIONS; i++) {
            LocalDate date = randomDate.nextDate();
            Assert.assertTrue(date.isAfter(startDate));
            Assert.assertTrue(date.isBefore(endDate));
        }
    }
}
