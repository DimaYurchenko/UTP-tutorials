package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {

    // (assignment 02)
    // attributes:
    // * salary
    // * manager (empty if at top of hierarchy)
    private BigDecimal salary;
    private Manager manager;

    protected Employee(String firstName, String surname, LocalDate birthdate, BigDecimal salary) {
        super(firstName, surname, birthdate);
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", salary=" + salary +
                ", manager=" + (getManager() == null ? "no manager" :
                manager.getFirstName() + " " + manager.getSurname());
    }

    // (assignment 03)
    // methods:
    // * salary is greater than given amount of money
    // * salary is less than given amount of money
    // * compare salary with other employee salary

    public boolean salaryIsGreater(BigDecimal salary) {
        if (salary.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Salary can not be negative");
        }

        return this.salary.doubleValue() > salary.doubleValue();
    }

    public boolean salaryIsSmaller(BigDecimal salary) {
        if (salary.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Salary can not be negative");
        }

        return this.salary.doubleValue() < salary.doubleValue();
    }

    public int compareSalary(Employee employee) {
        if (employee == null) {
            return -1;
        }

        return this.salary.compareTo(employee.getSalary());
    }
}