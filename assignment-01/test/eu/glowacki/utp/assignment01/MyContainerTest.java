package eu.glowacki.utp.assignment01;

import eu.glowacki.utp.assignment01.solution.Employee;
import eu.glowacki.utp.assignment01.solution.Money;
import eu.glowacki.utp.assignment01.solution.MyContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyContainerTest {

    private MyContainer<Employee, String> container;
    List<Employee> employees;
    private Employee emp1;
    private Employee emp2;
    private Employee emp3;

    @Before
    public void before() {
        emp1 = new Employee("Dmytro", 19, new Money(10000d, Money.Currency.USD));
        emp2 = new Employee("Alex", 23, new Money(99999d, Money.Currency.EUR));
        emp3 = new Employee("Michael", 20, new Money(123456d, Money.Currency.PLN));
        employees = List.of(emp1, emp2, emp3);
        container = new MyContainer<>(employees);
    }

    @Test
    public void elements() {
        Assert.assertEquals(employees, container.elements());
    }

    @Test
    public void constructorWithoutArguments() {
        Assert.assertNotNull(new MyContainer<>().elements());
    }

    @Test
    public void cloneElementAtIndex() {
        Employee clone = container.cloneElementAtIndex(0);
        Assert.assertEquals(container.get(0), clone);
        Assert.assertNotSame(container.get(0), clone);
    }

    @Test
    public void aggregateAllElements() {
        String expected = emp1.toString() +"\n" + emp2.toString() + "\n" + emp3.toString();
        String result = container.aggregateAllElements();
        Assert.assertEquals(expected, result);
    }
}
