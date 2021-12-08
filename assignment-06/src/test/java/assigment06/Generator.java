package assigment06;

import com.github.javafaker.Faker;
import eu.glowacki.utp.assigment06.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;

public class Generator {

    private Set<Student> students;
    private Set<Teacher> teachers;
    private Set<StudentGroup> studentGroups;
    private Set<Department> departments;
    private Set<Subject> subjects;

    private final int numStudents = 100;
    private final int numTeachers = 10;
    private final int numStudentGroups = 2;
    private final int numDepartments = 3;
    private final int numSubjects= 10;

    <E> E getRandomSetElement(Set<E> set) {
        return set.stream()
                .skip(new Random()
                        .nextInt(set.size()))
                .findFirst()
                .orElse(null);
    }

    <E> List<E> getRamdomListFromSet(Set<E> set, int size) {
        List<E> elements = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            elements.add(getRandomSetElement(set));
        }
        return elements;
    }


    @Before
    public void before(){
        Faker faker = new Faker();
        RandomDateGenarator birthDateGenerator = new RandomDateGenarator(LocalDate.of(1970, 1, 1), LocalDate.of(2000,1,1));
        students = new HashSet<>();
        teachers = new HashSet<>();
        studentGroups = new HashSet<>();
        departments = new HashSet<>();
        subjects = new HashSet<>();

        for (int i = 0; i < numStudents; i++) {
            students.add(new Student(faker.name().firstName(),
                    faker.name().lastName(),
                    birthDateGenerator.nextDate(),
                    Nationality.random()));
        }

        RandomDateGenarator hireDateGenerator = new RandomDateGenarator(LocalDate.of(2000, 1, 1), LocalDate.now());

        for (int i = 0; i < numTeachers; i++) {
            teachers.add(new Teacher(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    birthDateGenerator.nextDate(),
                    Nationality.random(),
                    hireDateGenerator.nextDate(),
                    AcademicDegree.random()
            ));
        }

        departments.add(new Department("Dept1", teachers.stream().toList().subList(0, 3)));
        departments.add(new Department("Dept2", teachers.stream().toList().subList(4, 6)));
        departments.add(new Department("Dept3", teachers.stream().toList().subList(7, 9)));

        List<String> subjectNames = List.of("ASD", "UTP", "SKJ", "MAD", "SAD", "SYC", "ALG", "TAK", "PPJ", "RBD");

        subjectNames.forEach(s -> subjects.add(
                new Subject(s,
                        getRandomSetElement(departments),
                        getRandomSetElement(teachers),
                        getRamdomListFromSet(students, 10)
                        )
        ));

        for (int i = 0; i < numStudentGroups; i++) {
            studentGroups.add(new StudentGroup(getRamdomListFromSet(students, 15)));
        }

    }



    @Test
    public void test() {
        Assert.assertEquals(students.size(), numStudents);
        Assert.assertEquals(teachers.size(), numTeachers);
        Assert.assertEquals(departments.size(), numDepartments);
        Assert.assertEquals(subjects.size(), numSubjects);
        Assert.assertEquals(studentGroups.size(), numStudentGroups);
    }

    @Test
    public void printResult() {
        System.out.println("students:");
        students.forEach(System.out::println);
        System.out.println("Teachers:");
        teachers.forEach(System.out::println);
        System.out.println("Departments:");
        departments.forEach(System.out::println);
        System.out.println("Subjects");
        subjects.forEach(System.out::println);
        System.out.println("Student groups");
        studentGroups.forEach(System.out::println);
    }
}
