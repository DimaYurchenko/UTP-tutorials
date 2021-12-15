package eu.glowacki.utp.assignment08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person> {

	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;

	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		// serialize birth date with getTime() method
		// encapsulate IOException in Assignment08Exception

		try {
			output.writeUTF(_firstName);
			output.writeUTF(_surname);
			output.writeLong(_birthdate.getTime());
		} catch (IOException e) {
			throw new Assignment08Exception("Serialization error", e);
		}
	}
	
	public static Person deserialize(DataInputStream input) throws Assignment08Exception {

		String name;
		String surname;
		long birthdate;

		try {
			name = input.readUTF();
			surname = input.readUTF();
			birthdate = input.readLong();
		} catch (IOException e) {
			throw new Assignment08Exception("Deserialization error", e);
		}

		return new Person(name, surname, new Date(birthdate));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(_firstName, person._firstName) && Objects.equals(_surname, person._surname) && Objects.equals(_birthdate, person._birthdate);
	}


	@Override
	public int compareTo(Person otherPerson) {
		int surnameComp = _surname.compareTo(otherPerson.getSurname());

		if (surnameComp == 0) {
			int nameComp = _firstName.compareTo(otherPerson.getFirstName());

			if (nameComp == 0) {
				return _birthdate.compareTo(otherPerson.getBirthDate());
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

	public String getFirstName() {
		return _firstName;
	}

	public String getSurname() {
		return _surname;
	}

	public Date getBirthDate() {
		return _birthdate;
	}
}