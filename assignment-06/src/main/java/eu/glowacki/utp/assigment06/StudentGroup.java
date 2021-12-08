package eu.glowacki.utp.assigment06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentGroup implements Comparable<StudentGroup> {

    private final String name;
    private final List<Student> students;
    private static int count = 0;

    public StudentGroup() {
        this.name = "student_group" + ++count;
        students = new ArrayList<>();
    }

    public StudentGroup(List<Student> students) {
        this();
        addStudents(students);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new NullPointerException();
        }
        students.add(student);
    }

    public void addStudents(List<Student> students) {
        if (students == null) {
            throw new NullPointerException();
        }
        this.students.addAll(students);
    }

    @Override
    public int compareTo(StudentGroup o) {
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup that = (StudentGroup) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }


}