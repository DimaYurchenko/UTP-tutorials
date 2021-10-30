package eu.glowacki.utp.assignment03.employee;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {

	// (assignment 02)
	// attributes:
	// * first name
	// * surname
	// * birth date
	// * age (derived --- computed based on birth date)

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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate;
    }
	
	// (assignment 03)
	// methods:
	// * is older than other person
	// * is younger than other person
	// * compare age with other person's age

    public boolean isOlder(Person person) {
        if (person == null) {
            return false;
        }

        return this.birthDate.isBefore(person.getBirthDate());
    }


    public boolean isYounger(Person person) {
        if (person == null) {
            return false;
        }

        return this.birthDate.isAfter(person.getBirthDate());
    }

    public int compareAge(Person person) {
        if (person == null) {
            return -1;
        }

        return this.birthDate.compareTo(person.getBirthDate());
    }
}