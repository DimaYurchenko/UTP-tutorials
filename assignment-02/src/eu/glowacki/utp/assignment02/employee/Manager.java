package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class Manager extends Worker {

	// attributes
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)
	private List<Employee> subordinates;

	public Manager(String firstName, String surname, LocalDate birthdate,
				   BigDecimal salary, LocalDate employmentDate,
				   BigDecimal bonus, List<Employee> subordinates) {
		super(firstName, surname, birthdate, salary, employmentDate, bonus);
		setSubordinates(subordinates);
	}

	//when adding subordinates call subordinate.setManager(this) for each subordinate
	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
		subordinates.forEach(sub -> sub.setManager(this));
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public List<Employee> getAllSubordinates() {
		List <Employee> allSubordinates = new LinkedList<>(subordinates);

		for (Employee emp : subordinates) {
			if (emp instanceof Manager)
				allSubordinates.addAll(((Manager) emp).getAllSubordinates());
		}

		return allSubordinates;
	}


}