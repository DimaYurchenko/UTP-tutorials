package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {

	private BigDecimal salary;
	private Manager manager;

	//
	// attributes:
	// * salary (use BigDecimal type for representing currency values)
	// * manager (empty if at top of hierarchy)
	
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
}