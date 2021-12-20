package eu.glowacki.utp.assignment09;

import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class PeselTest {

    private Pesel male;
    private Pesel female;

    @Before
    public void before() {
        male = new Pesel("02262062134"); //20 06 2002
        female = new Pesel("90050717449"); // 07 05 1990
    }

    @Test
    public void validationTest() {

        try {
            Method isValid = Pesel.class.getDeclaredMethod("isValid");
            isValid.setAccessible(true);

            Assert.assertTrue((Boolean) isValid.invoke(male));
            Assert.assertTrue((Boolean) isValid.invoke(female));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void birthDateTest() {

        try {
            Method getBirthDate = Pesel.class.getDeclaredMethod("getBirthDate");
            getBirthDate.setAccessible(true);

            Date expected = new GregorianCalendar(2002, Calendar.JUNE, 20).getTime();
            Date actual = (Date) getBirthDate.invoke(male);
            Assert.assertEquals(expected, actual);

            expected = new GregorianCalendar(1990, Calendar.MAY, 7).getTime();
            actual = (Date) getBirthDate.invoke(female);
            Assert.assertEquals(expected, actual);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sexExtractionTest() {

        try {
            Method getSex = Pesel.class.getDeclaredMethod("getSex");
            getSex.setAccessible(true);

            Assert.assertEquals(Pesel.Sex.MALE, getSex.invoke(male));
            Assert.assertEquals(Pesel.Sex.FEMALE, getSex.invoke(female));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
