package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class Worker extends Employee {

	// (assignment 02)
	// attributes:
	// * employment date
	// * bonus

    private LocalDate employmentDate;
    private BigDecimal bonus;
    private boolean hasBonus;

    public Worker(String firstName, String surname, LocalDate birthdate, BigDecimal salary,
                  LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, surname, birthdate, salary);
        this.employmentDate = employmentDate;
        this.bonus = bonus;
        hasBonus = bonus.compareTo(BigDecimal.ZERO) != 0;
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
        hasBonus = bonus.compareTo(BigDecimal.ZERO) != 0;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", employmentDate=" + employmentDate +
                ", bonus=" + bonus;
    }
	
	// (assignment 03)
	// attributes:
	// * has bonus
	//
	// methods:
	// * seniority is longer than given number of years (seniority - sta)
	// * seniority is longer than given number of months
	// * has bonus greater than given amount of money

    public boolean seniorityIsLongerThanYears(int years) {
        if (years < 0) {
            throw new IllegalArgumentException("Number of years can not be negative");
        }

        int yearsBetween = (int) ChronoUnit.YEARS.between(
                YearMonth.from(employmentDate),
                YearMonth.from(LocalDate.now())
        );

        return yearsBetween > years;
    }

    public boolean seniorityIsLongerThanMonths(int months) {
        if (months < 0) {
            throw new IllegalArgumentException("Number of months can not be negative");
        }

        int monthsBetween = (int) ChronoUnit.MONTHS.between(
                YearMonth.from(employmentDate),
                YearMonth.from(LocalDate.now())
        );

        return monthsBetween > months;
    }

    public boolean bonusIsGreaterThan(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Amount of money can not be negative");
        }

        return bonus.doubleValue() > amount.doubleValue();
    }


}