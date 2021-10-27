package eu.glowacki.utp.assignment02.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import eu.glowacki.utp.assignment02.employee.Trainee;
import eu.glowacki.utp.assignment02.employee.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.glowacki.utp.assignment02.HumanResourcesStatistics;
import eu.glowacki.utp.assignment02.employee.Employee;
import eu.glowacki.utp.assignment02.employee.Manager;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;

public class HumanResourcesStatisticsTest {
	
	// Create a HR structure which resembles the below one:
	//
	// Director (an instance of Manager class (Director does not have a manager)
	//   |- Manager01
	//        |- Worker
	//        |- Worker
	//        |- Trainee
	//        |- ...
	//   |- Manager02
	//        |- ...
	//   |- ...
	//   |- Worker
	//   |- Worker
	//   |- Trainee
	
	private List<Employee> _allEmployees; // all employees ---  i.e. Workers, Trainees and their Managers and top Director (also an instance of Manager class)
	private Worker w1,w2,w3,w4,w5,w6,w7,w8,w9,w10,w11,w12,w13,w14;
	private Trainee tr1, tr2, tr3;
	private Manager mngr1, mngr2, director;

	@Before
	public void before() {
		w1 = new Worker("Zachary", "Harris", LocalDate.of(1982, 8, 10), new BigDecimal(93789), LocalDate.of(2014, 8, 19), new BigDecimal(0));
		w2 = new Worker("Elizabeth", "Hanson", LocalDate.of(1993, 8, 8), new BigDecimal(89365), LocalDate.of(2015, 2, 14), new BigDecimal(9289));
		w3 = new Worker("William", "Ball", LocalDate.of(1993, 12, 24), new BigDecimal(41401), LocalDate.of(2018, 1, 21), new BigDecimal(7584));
		w4 = new Worker("Jeffrey", "Sanchez", LocalDate.of(1981, 5, 21), new BigDecimal(73174), LocalDate.of(2009, 9, 19), new BigDecimal(9095));
		w5 = new Worker("Valerie", "Fernandez", LocalDate.of(1982, 8, 6), new BigDecimal(41770), LocalDate.of(2016, 10, 9), new BigDecimal(0));
		w6 = new Worker("Monica", "Adams", LocalDate.of(1992, 11, 14), new BigDecimal(99288), LocalDate.of(2017, 4, 6), new BigDecimal(7768));
		w7 = new Worker("David", "Johnson", LocalDate.of(1991, 3, 30), new BigDecimal(20790), LocalDate.of(2009, 10, 17), new BigDecimal(3684));
		w8 = new Worker("Melanie", "Mendez", LocalDate.of(1993, 10, 13), new BigDecimal(46016), LocalDate.of(2019, 9, 3), new BigDecimal(5667));
		w9 = new Worker("Melissa", "Allison", LocalDate.of(1981, 10, 8), new BigDecimal(38334), LocalDate.of(2016, 6, 27), new BigDecimal(0));
		w10 = new Worker("Susan", "Robles", LocalDate.of(1988, 5, 1), new BigDecimal(34571), LocalDate.of(2012, 1, 6), new BigDecimal(202));
		w11 = new Worker("Timothy", "Perry", LocalDate.of(1997, 11, 25), new BigDecimal(68281), LocalDate.of(2016, 5, 10), new BigDecimal(3663));
		w12 = new Worker("Sara", "Austin", LocalDate.of(1985, 9, 6), new BigDecimal(20286), LocalDate.of(2008, 11, 2), new BigDecimal(5985));
		w13 = new Worker("Kate", "Buchanan", LocalDate.of(1991, 12, 12), new BigDecimal(41910), LocalDate.of(2011, 2, 22), new BigDecimal(0));
		w14 = new Worker("Taylor", "Nichols", LocalDate.of(1998, 11, 25), new BigDecimal(39713), LocalDate.of(2010, 12, 16), new BigDecimal(7195));
		tr1 = new Trainee("Patrik", "Dickerson", LocalDate.of(1998, 3, 1), new BigDecimal(10234), LocalDate.of(2021, 10, 16), 90);
		tr2 = new Trainee("Ronan", "Arnolds", LocalDate.of(1996, 7, 19), new BigDecimal(9063), LocalDate.of(2021, 9, 23), 120);
		tr3 = new Trainee("Josh", "Fowler", LocalDate.of(1999, 9, 21), new BigDecimal(7100), LocalDate.of(2021, 11, 3), 60);
		mngr1 = new Manager("James", "Downs", LocalDate.of(1989, 8, 19), new BigDecimal(152001), LocalDate.of(2009, 10, 19), new BigDecimal(6367),
				Arrays.asList(w1,w2,w3,w4,w5,w6, tr1));
		mngr2 = new Manager("Jason", "Ausher", LocalDate.of(1991, 3, 30), new BigDecimal(210686), LocalDate.of(2021, 3, 17), new BigDecimal(30000),
				Arrays.asList(w7,w8,w9,w10,w11,tr2));
		director = new Manager("Christopher", "Freeman", LocalDate.of(1980, 2, 26), new BigDecimal(220863), LocalDate.of(2007, 3, 23), new BigDecimal(9983),
				Arrays.asList(mngr1,mngr2,w12,w13,w14,tr3));

		_allEmployees = Arrays.asList(w1,w2,w3,w4,w5,w6,w7,w8,w9,w10,w11,w12,w13,w14,tr1,tr2,tr3,mngr1,mngr2,director);
	}


	@Test
	public void payroll() {
		HumanResourcesStatistics.payroll(_allEmployees).forEach(System.out::println);
	}

	@Test
	public void subordinatesPayroll() {
		HumanResourcesStatistics.subordinatesPayroll(director).forEach(System.out::println);
	}

	@Test
	public void bonusTotal() {
		BigDecimal expectedBonus = new BigDecimal(106482);
		BigDecimal actualBonus = HumanResourcesStatistics.bonusTotal(_allEmployees);
		Assert.assertEquals(expectedBonus, actualBonus);
	}

	@Test
	public void longestSeniority() {
		Worker expectedLongestSeniority = director;
		Worker actualLongestSeniority = HumanResourcesStatistics.longestSeniorityWorker(_allEmployees);
		Assert.assertEquals(expectedLongestSeniority, actualLongestSeniority);
	}

	@Test
	public void highestSalary() {
		BigDecimal expectedSalary = director.getSalary();
		BigDecimal actualSalary = HumanResourcesStatistics.highestSalary(_allEmployees);
		Assert.assertEquals(expectedSalary, actualSalary);
	}

	@Test
	public void highestSalaryWithBonus() {
		BigDecimal expectedSalary = mngr2.getSalary().add(mngr2.getBonus());
		BigDecimal actualSalary = HumanResourcesStatistics.highestSalaryWithBonus(_allEmployees);
		Assert.assertEquals(expectedSalary, actualSalary);
		System.out.println(HumanResourcesStatistics.highestSalaryWithBonus(_allEmployees));
	}

	@Test
	public void employeesWhoseSurnameStartsWithA() {
		List<Employee> expectedEmployees = Arrays.asList(w6, w9, w12, tr2, mngr2);
		List<Employee> actualEmployees = HumanResourcesStatistics.employeesSurnameA(director);
		Assert.assertTrue(expectedEmployees.size() == actualEmployees.size()
				&& actualEmployees.containsAll(expectedEmployees)
				&& expectedEmployees.containsAll(actualEmployees));
	}

	@Test
	public void earnMoreThen() {
		BigDecimal AMMOUNT = new BigDecimal(90000);
		List<Employee> expectedEmployees = Arrays.asList(w1, w6, mngr1, mngr2, director);
		List<Employee> actualEmployees = HumanResourcesStatistics.earnMoreThan(_allEmployees, AMMOUNT);
		Assert.assertTrue(expectedEmployees.size() == actualEmployees.size()
				&& actualEmployees.containsAll(expectedEmployees)
				&& expectedEmployees.containsAll(actualEmployees));

	}

	/// ...
	// rest of the methods specified in the assignment description
}