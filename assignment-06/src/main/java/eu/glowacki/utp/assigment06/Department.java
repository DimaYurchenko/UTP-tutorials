package eu.glowacki.utp.assigment06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department implements Comparable<Department> {

    private final String name;
    private final List<Teacher> employees;


    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public Department(String name, List<Teacher> teachers) {
        this(name);
        addTeachers(teachers);
    }

    public void addTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new NullPointerException();
        }
        employees.add(teacher);
    }

    public void addTeachers(List<Teacher> teachers) {
        if (teachers == null) {
            throw new NullPointerException();
        }

        this.employees.addAll(teachers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }

    @Override
    public int compareTo(Department o) {
        return name.compareTo(o.getName());
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getEmployees() {
        return employees;
    }

}
