package eu.glowacki.utp.assignment04.test;

import eu.glowacki.utp.assignment04.MyInputParser;
import eu.glowacki.utp.assignment04.Person;
import eu.glowacki.utp.assignment04.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PersonDatabaseTest {

    private PersonDatabase personDatabase;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void before() {
        personDatabase = new PersonDatabase(
                MyInputParser.parse(new File("test/eu/glowacki/utp/assignment04/test/data.txt")));
    }

    @Test
    public void sortedByFirstNameTest() {
        List<Person> people = personDatabase.sortedByFirstName();

        Assert.assertEquals("Aaron", people.get(0).get_firstName());
        Assert.assertEquals("William", people.get(people.size() - 1).get_firstName());
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthdateTest() throws ParseException {
        List<Person> people = personDatabase.sortedBySurnameFirstNameAndBirthdate();


        Assert.assertEquals("Adams", people.get(0).get_surname());
        Assert.assertEquals("Brian", people.get(0).get_firstName());
        Assert.assertEquals(simpleDateFormat.parse("1961-12-19"), people.get(0).get_birthdate());

        Assert.assertEquals("Wong", people.get(people.size() - 1).get_surname());
        Assert.assertEquals("Andrea", people.get(people.size() - 1).get_firstName());
        Assert.assertEquals(simpleDateFormat.parse("1985-11-17"), people.get(people.size() - 1).get_birthdate());
    }

    @Test
    public void sortedByBirthdateTest() throws ParseException {
        List<Person> people = personDatabase.sortedByBirthdate();

        Assert.assertEquals(simpleDateFormat.parse("1961-12-19"), people.get(0).get_birthdate());
        Assert.assertEquals(simpleDateFormat.parse("2021-10-05"), people.get(people.size() - 1).get_birthdate());
    }

    @Test
    public void bornOnDayTest() throws ParseException {
        List<Person> people = personDatabase.bornOnDay(simpleDateFormat.parse("1967-07-24"));

        Assert.assertEquals(3, people.size());
    }

}