package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee {

	// (assignment 02)
	// attributes:
	// * practice start date
	// * practice length (in days)

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
	
	// (assignment 03)
	// * practice length is shorter than given number of days
	// * practice length is longer than given number of days

    public boolean apprenticeshipIsShorterThan(short days) {
        if (days < 0) {
            throw new IllegalArgumentException("Number of days can not be negative");
        }

        return apprenticeshipLength < days;
    }

    public boolean apprenticeshipIsLongerThan(short days) {
        if (days < 0) {
            throw new IllegalArgumentException("Number of days can not be negative");
        }

        return apprenticeshipLength > days;
    }
}