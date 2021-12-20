package eu.glowacki.utp.assignment08;

import eu.glowacki.utp.assignment08.comparators.BirthdateComparator;
import eu.glowacki.utp.assignment08.comparators.FirstNameComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public final class PersonDatabase {

	private final List<Person> people;
	private final Map<Date, List<Person>> birthdayMap;

	public PersonDatabase(List<Person> people) {
		this.people = people;
		birthdayMap = new HashMap<>();

		people.forEach(person -> {
			Date date = person.getBirthDate();
			if (birthdayMap.containsKey(date)) {
				birthdayMap.get(date).add(person);
			} else {
				birthdayMap.put(date, new LinkedList<>(List.of(person)));
			}
		});
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {

		people.forEach(person -> {
			try {
				person.serialize(output);
			} catch (Assignment08Exception e) {
				e.printStackTrace();
			}
		});
	}

	// assignment 8 - factory method based on deserialization
	public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
		List<Person> people = new LinkedList<>();

		try {
			while (input.available() > 0) {
				people.add(Person.deserialize(input));
			}
		} catch (IOException e) {
			throw new Assignment08Exception("Deserialization error", e);
		}

		return new PersonDatabase(people);
	}

	// assignment 4
	public List<Person> sortedByFirstName() {
		people.sort(new FirstNameComparator());
		return people;
	}

	// assignment 4
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		people.sort(Person::compareTo);
		return people;
	}

	// assignment 4
	public List<Person> sortedByBirthdate() {
		people.sort(new BirthdateComparator());
		return people;
	}

	// assignment 4
	public List<Person> bornOnDay(Date date) {
		return birthdayMap.get(date);
	}

	public List<Person> getPeople() {
		return people;
	}
}