package eu.glowacki.utp.assignment02;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import eu.glowacki.utp.assignment02.employee.Employee;
import eu.glowacki.utp.assignment02.employee.Manager;
import eu.glowacki.utp.assignment02.employee.Trainee;
import eu.glowacki.utp.assignment02.employee.Worker;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;

public final class HumanResourcesStatistics {

	public static List<PayrollEntry> payroll(List<Employee> employees) {
		if (employees == null) {
			return null;
		}
		return employees.stream().map(
				employee -> new PayrollEntry(employee, employee.getSalary(),
						employee instanceof Worker ?
								((Worker) employee).getBonus() : null)
		).collect(Collectors.toList());

	}

	// payroll for all subordinates
	public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
		if (manager == null) {
			return null;
		}

		return payroll(manager.getAllSubordinates());
	}

	public static BigDecimal bonusTotal(List<Employee> employees) {
		if (employees == null) {
			return null;
		}

		return employees.stream().filter(
				employee -> employee instanceof Worker
		).map(
				employee -> (Worker) employee
		).reduce(
				BigDecimal.ZERO,
				(subtotal, worker) -> subtotal.add(worker.getBonus()),
				BigDecimal::add
		);
	}

	public static Worker longestSeniorityWorker(List <Employee> employees) throws NoSuchElementException {
		if (employees == null) {
			return null;
		}

		List<Worker> workers = employees.stream()
				.filter(employee -> employee instanceof Worker)
				.map(employee -> (Worker)employee)
				.collect(Collectors.toList());

		return workers
				.stream()
				.min(Comparator.comparing(Worker::getEmploymentDate))
				.orElseThrow(NoSuchElementException::new);
	}

	public static BigDecimal highestSalary(List<Employee> employees) throws NoSuchElementException {
		if (employees == null) {
			return null;
		}

		return employees.stream()
				.max(Comparator.comparing(Employee::getSalary))
				.orElseThrow(NoSuchElementException::new)
				.getSalary();
	}

	public static BigDecimal highestSalaryWithBonus(List<Employee> employees) throws NoSuchElementException {
		if (employees == null) {
			return null;
		}

		List<Worker> workers = employees.stream()
				.filter(employee -> employee instanceof Worker)
				.map(employee -> (Worker)employee)
				.collect(Collectors.toList());

		return workers.stream()
				.map(worker -> worker.getSalary().add(worker.getBonus()))
				.max(BigDecimal::compareTo)
				.orElseThrow(NoSuchElementException::new);
	}

	public static List<Employee> employeesSurnameA(Manager manager) {
		if (manager == null) {
			return null;
		}

		return manager.getAllSubordinates()
				.stream()
				.filter(employee -> employee.getSurname().startsWith("A"))
				.collect(Collectors.toList());
	}

	public static List<Employee> earnMoreThan(List<Employee> employees, BigDecimal ammount) {
		if (employees == null) {
			return null;
		}

		return employees.stream()
				.filter(employee -> employee.getSalary().compareTo(ammount) > 0)
				.collect(Collectors.toList());
	}
}