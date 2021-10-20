package eu.glowacki.utp.assignment01;

import eu.glowacki.utp.assignment01.sample.Person;
import eu.glowacki.utp.assignment01.solution.Employee;
import eu.glowacki.utp.assignment01.solution.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {

    private Employee employee;

    @Before
    public void before() {
        employee = new Employee("Dmytro", 19, new Money(99.999, Money.Currency.USD));
    }

    @Test
    public void aggregate() {
        String aggregate = employee.aggregate(null);
        Assert.assertEquals(employee.toString(), aggregate);

        final Employee testEmp = new Employee("Vlad", 21, new Money(0d, Money.Currency.USD));
        Assert.assertEquals(testEmp.toString() + "\n" + employee.toString(), employee.aggregate(testEmp.toString()));
    }

    @Test
    public void deepClone() {
        Employee clone = employee.deepClone();
        Assert.assertEquals(employee.getAge(), clone.getAge());
        Assert.assertEquals(employee.getName(), clone.getName());
        Assert.assertEquals(employee.getSalary(), clone.getSalary());
        Assert.assertNotSame(employee.getSalary(), clone.getSalary());
        Assert.assertEquals(employee, clone);
        Assert.assertNotSame(employee, clone);
    }
}
