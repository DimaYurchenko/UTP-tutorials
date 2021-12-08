package eu.glowacki.utp.assigment06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subject implements Comparable<Subject>{

    private final String name;
    private final Department supervisingDepartment;
    private final Teacher lecturer;
    private final List<Student> students;

    public Subject(String name, Department supervisingDepartment, Teacher lecturer, List<Student> students) {
        this.name = name;
        this.supervisingDepartment = supervisingDepartment;
        this.lecturer = lecturer;
        this.students = new ArrayList<>();
        addStudents(students);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new NullPointerException();
        }
        this.students.add(student);
    }

    public void addStudents(List<Student> students) {
        if (students == null) {
            throw new NullPointerException();
        }
        this.students.addAll(students);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", supervisingDepartment=" + supervisingDepartment +
                ", lecturer=" + lecturer +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Subject o) {
        return name.compareTo(o.getName());
    }

    public String getName() {
        return name;
    }

    public Department getSupervisingDepartment() {
        return supervisingDepartment;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public List<Student> getStudents() {
        return students;
    }




}
