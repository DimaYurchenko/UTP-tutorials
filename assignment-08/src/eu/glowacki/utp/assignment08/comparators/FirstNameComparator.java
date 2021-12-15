package eu.glowacki.utp.assignment08.comparators;

import eu.glowacki.utp.assignment08.Person;
import java.util.Comparator;

public class FirstNameComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {

		return person1.getFirstName().compareTo(person2.getFirstName());
	}
}