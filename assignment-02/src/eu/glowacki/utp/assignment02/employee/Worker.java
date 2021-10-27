package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

	// attributes
	// * employment date
	// * bonus
	private LocalDate employmentDate;
	private BigDecimal bonus;
	
	public Worker(String firstName, String surname, LocalDate birthdate, BigDecimal salary,
				  LocalDate employmentDate, BigDecimal bonus) {
		super(firstName, surname, birthdate, salary);
		this.employmentDate = employmentDate;
		this.bonus = bonus;
	}

	public LocalDate getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(LocalDate employmentDate) {
		this.employmentDate = employmentDate;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return super.toString() +
				", employmentDate=" + employmentDate +
				", bonus=" + bonus;
	}
}