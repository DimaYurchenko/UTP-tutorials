package eu.glowacki.utp.assignment01.solution;

import eu.glowacki.utp.assignment01.IAggregable;
import eu.glowacki.utp.assignment01.IContainer;
import eu.glowacki.utp.assignment01.IDeeplyCloneable;
import eu.glowacki.utp.assignment01.sample.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee implements IAggregable<Employee, String>, IDeeplyCloneable<Employee> {

    private String name;
    private int age;
    private Money salary;

    public Employee() {
    }

    public Employee(String name, int age, Money salary) {
        this.name = name;
        this.age = age;
        this.salary = salary.deepClone();
    }

    @Override
    public String aggregate(String intermediateResult) {
        if  (intermediateResult == null) {
            return toString();
        }

        return intermediateResult + "\n" + toString();
    }

    @Override
    public Employee deepClone() {
        return new Employee(name, age, salary.deepClone());
    }

    @Override
    public String toString() {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary.toString() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Employee employee = (Employee) obj;
        // field comparison
        return Objects.equals(name, employee.getName())
                && Objects.equals(age, employee.getAge())
                && Objects.equals(salary, employee.getSalary());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Money getSalary() {
        return salary;
    }

    public void setSalary(Money salary) {
        this.salary = salary.deepClone();
    }
}
