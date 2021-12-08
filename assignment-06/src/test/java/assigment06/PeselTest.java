package assigment06;

import eu.glowacki.utp.assigment06.Pesel;
import eu.glowacki.utp.assigment06.RandomDateGenarator;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PeselTest {

    @Test
    public void validateTest() {
        Assert.assertTrue(Pesel.validate("99032866616"));
        Assert.assertTrue(Pesel.validate("81050225986"));
        Assert.assertTrue(Pesel.validate("60122611874"));
        Assert.assertTrue(Pesel.validate("68100439993"));
        Assert.assertTrue(Pesel.validate("70061943297"));
        Assert.assertTrue(Pesel.validate("73012881554"));
        Assert.assertTrue(Pesel.validate("71010948639"));
        Assert.assertTrue(Pesel.validate("72072245256"));
    }

    @Test
    public void generateTest() {
        RandomDateGenarator randomDate = new RandomDateGenarator(
                        LocalDate.of(1970,1, 1),
                        LocalDate.now()
        );

        final int ITERATIONS = 100;

        for (int i = 0; i < ITERATIONS; i++) {
            Assert.assertTrue(
                    Pesel.validate(new Pesel(randomDate.nextDate()))
            );
        }

    }

}
