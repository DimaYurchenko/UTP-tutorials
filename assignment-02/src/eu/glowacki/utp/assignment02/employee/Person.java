package eu.glowacki.utp.assignment02.employee;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Date;

public abstract class Person {



	// To implement an attribute means that you provide a backing field and
	// getter or optionally setter for read-write properties/attributes
	// 
	// NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
	// THOSE SHOULD BE COMPUTED ON-LINE
	//
	// attributes:
	// * first name (read-only)
	// * surname (read-only)
	// * birth date (read-only) --- date MUST BE represented by an instance of
	// the type designed for date representation (either Date or LocalDate)
	//
	// * age (derived --- computed based on birth date) --- implemented as a
	// getter calculating the difference between the current date and birth date

	private final String firstName; // backing field
	private	final String surname;
	private final LocalDate birthDate;

	protected Person(String firstName, String surname, LocalDate birthDate) {
		this.firstName = firstName;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public short getAge() {
		return (short) Period.between(birthDate, LocalDate.now()).getYears();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
}