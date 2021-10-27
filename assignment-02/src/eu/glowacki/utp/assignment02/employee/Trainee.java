package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee {

	// attributes:
	// * apprenticeship start date
	// * apprenticeship length (in days)
	private LocalDate apprenticeshipStart;
	private int apprenticeshipLength;

	public Trainee(String firstName, String surname, LocalDate birthdate,
				   BigDecimal salary, LocalDate apprenticeshipStart, int apprenticeshipLength) {
		super(firstName, surname, birthdate, salary);
		this.apprenticeshipStart = apprenticeshipStart;
		this.apprenticeshipLength = apprenticeshipLength;
	}

	public LocalDate getApprenticeshipStart() {
		return apprenticeshipStart;
	}

	public void setApprenticeshipStart(LocalDate apprenticeshipStart) {
		this.apprenticeshipStart = apprenticeshipStart;
	}

	public int getApprenticeshipLength() {
		return apprenticeshipLength;
	}

	public void setApprenticeshipLength(int apprenticeshipLength) {
		this.apprenticeshipLength = apprenticeshipLength;
	}

	@Override
	public String toString() {
		return super.toString() +
				", apprenticeshipStart=" + apprenticeshipStart +
				", apprenticeshipLength=" + apprenticeshipLength;
	}
}