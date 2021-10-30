package eu.glowacki.utp.assignment03;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import eu.glowacki.utp.assignment03.employee.Employee;
import eu.glowacki.utp.assignment03.employee.Trainee;
import eu.glowacki.utp.assignment03.employee.Worker;

public final class HumanResourceStatistics {
	
	private HumanResourceStatistics() {}

	// The best solution is to impleent the below features as static methods having a list of Employee as the first input argument.
	// In addition the list of arguments may be augmented with parameters required for the given feature.

	// (assignment 03)
	// methods:
	//
	// * search for Employees older than given employee and earning less than him
	public static List<Employee> olderThanAndEarnMore(List<Employee> allEmployees, Employee employee) {

		return allEmployees.stream()
				.filter(emp -> emp.isOlder(employee) && emp.salaryIsSmaller(employee.getSalary()))
				.collect(Collectors.toList());
	}
	
	//
	// * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
	public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
		List<Trainee> trainees = List.copyOf(
				allEmployees.stream()
					.filter(employee -> employee instanceof Trainee)
					.map(employee -> (Trainee)employee)
					.filter(trainee -> trainee.apprenticeshipIsLongerThan((short)daysCount))
					.collect(Collectors.toList())
		);

		trainees.forEach(trainee -> trainee.setSalary(trainee.getSalary().multiply(BigDecimal.valueOf(1.05))));

		return trainees;
	}
	
	//
	// * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
	public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
		return null;
	}
	
	//
	// * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
	public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
		return null;
	}
	
	//
	// * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
	public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
		return null;
	}
	
	//
	// * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
	public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
		return null;
	}
}