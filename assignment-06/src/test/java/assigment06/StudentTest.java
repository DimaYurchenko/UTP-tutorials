package assigment06;

import eu.glowacki.utp.assigment06.Nationality;
import eu.glowacki.utp.assigment06.Student;
import org.junit.Test;

import java.time.LocalDate;

public class StudentTest {

    @Test
    public void test() {
        Student s1 = new Student("Dima", "Yurchenko", LocalDate.of(2002, 6, 20), Nationality.Ukrainian);
        Student s2 = new Student("Dima", "Yurchenko", LocalDate.of(2002, 6, 20), Nationality.Ukrainian);

        System.out.println(s1.equals(s2));
    }
}
