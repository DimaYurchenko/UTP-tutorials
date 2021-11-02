package eu.glowacki.utp.assigment03.test;

import eu.glowacki.utp.assignment03.HumanResourceStatistics;
import eu.glowacki.utp.assignment03.employee.Employee;
import eu.glowacki.utp.assignment03.employee.Manager;
import eu.glowacki.utp.assignment03.employee.Trainee;
import eu.glowacki.utp.assignment03.employee.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class HumanResourceStatisticsTest {

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
        w12 = new Worker("Sara", "Austin", LocalDate.of(1985, 9, 6), new BigDecimal(20286), LocalDate.of(2008, 11, 2), new BigDecimal(100));
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
    public void olderThanAndEarnMoreTest() {
        List<Employee> expected = List.of(w4, w5, w9);
        List<Employee> actual = HumanResourceStatistics.olderThanAndEarnMore(_allEmployees, w1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void practiceLengthLongerThanTest() {
        final int DAYS = 80;
        List<Trainee> expected = List.of(tr1, tr2);
        List<Trainee> actual = HumanResourceStatistics.practiceLengthLongerThan(_allEmployees, DAYS);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void seniorityLongerThanMonthTest() {
        final int MONTHS = 150;
        List<Worker> expected = List.of(w12, director);
        List<Worker> actual = HumanResourceStatistics.seniorityLongerThan(_allEmployees, MONTHS);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void seniorityBetweenOneAndThreeYearsTest() {
        List<Worker> expected = List.of(w3, w8);
        List<Worker> actual = HumanResourceStatistics.seniorityBetweenOneAndThreeYears(_allEmployees);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void seniorityLongerThanWorkerTest() {
        List<Worker> expected = List.of(w7, w12);
        List<Worker> actual = HumanResourceStatistics.seniorityLongerThan(_allEmployees, w14);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void eniorityBetweenTwoAndFourYearsAndAgeGreaterThanTest() {
        final int YEARS = 28;
        List<Worker> expected = List.of(w6);
        List<Worker> actual = HumanResourceStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(_allEmployees, YEARS);
        Assert.assertEquals(expected, actual);
    }



}