package eu.glowacki.utp.assignment04;

import eu.glowacki.utp.assignment04.comparators.BirthdateComparator;
import eu.glowacki.utp.assignment04.comparators.FirstNameComparator;

import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {

	private final List<Person> people;
	private final Map<Date, List<Person>> birthdayMap;

	public PersonDatabase(List<Person> people) {
		this.people = people;
		birthdayMap = new HashMap<>();

		people.forEach(person -> {
			Date date = person.get_birthdate();
			if (birthdayMap.containsKey(date)) {
				birthdayMap.get(date).add(person);
			} else {
				birthdayMap.put(date, new LinkedList<>(List.of(person)));
			}
		});
	}

	public List<Person> sortedByFirstName() {
		people.sort(new FirstNameComparator());
		return people; // external rule for ordering (based on Comparator --- FirstNameComparator)
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		people.sort(Person::compareTo);
		return people; // natural order (Comparable)
	}

	
	public List<Person> sortedByBirthdate() {
		people.sort(new BirthdateComparator());
		return people; // external rule for ordering (based on Comparator --- BirthdateComparator)
	}
	
	public List<Person> bornOnDay(Date date) {
		return birthdayMap.get(date);
	}

	public List<Person> getPeople() {
		return people;
	}
}