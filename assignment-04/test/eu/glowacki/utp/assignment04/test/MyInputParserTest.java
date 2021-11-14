package eu.glowacki.utp.assignment04.test;

import eu.glowacki.utp.assignment04.MyInputParser;
import eu.glowacki.utp.assignment04.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public final class MyInputParserTest {

    private List<Person> personList;

    @Before
    public void before() {
        personList = MyInputParser.parse(new File("test/eu/glowacki/utp/assignment04/test/data.txt"));

    }

    @Test
    public void parserTest() {
        personList.forEach(System.out::println);
        personList.forEach(Assert::assertNotNull);
    }

}