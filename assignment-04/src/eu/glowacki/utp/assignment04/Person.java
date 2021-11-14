package eu.glowacki.utp.assignment04;

import java.util.Date;

public final class Person implements Comparable<Person> {
	
	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	@Override
	public int compareTo(Person otherPerson) {
		// natural order based on:
		// (1) surname;
		// (2) first name;
		// (3) birth date.
		// TODO Auto-generated method stub
		int surnameComp = _surname.compareTo(otherPerson.get_surname());

		if (surnameComp == 0) {
			int nameComp = _firstName.compareTo(otherPerson.get_firstName());

			if (nameComp == 0) {
				return _birthdate.compareTo(otherPerson.get_birthdate());
			}

			return nameComp;
		}
		return surnameComp;
	}

	@Override
	public String toString() {
		return "surname='" + _surname + '\'' +
				", firstname='" + _firstName + '\'' +
				", birthdate=" + _birthdate;
	}

	public String get_firstName() {
		return _firstName;
	}

	public String get_surname() {
		return _surname;
	}

	public Date get_birthdate() {
		return _birthdate;
	}
}