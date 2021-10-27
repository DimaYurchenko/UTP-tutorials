package eu.glowacki.utp.assignment02.payroll;

import java.math.BigDecimal;

import eu.glowacki.utp.assignment02.employee.Employee;

public final class PayrollEntry {

	private final Employee _employee;
	private final BigDecimal _salaryPlusBonus;
	
	public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
		_employee = employee;
		if (salary == null)
			salary = new BigDecimal(0);
		if (bonus == null )
			bonus = new BigDecimal(0);
		_salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
	}

	@Override
	public String toString() {
		return "PayrollEntry{" +
				"_employee=" + _employee +
				", _salaryPlusBonus=" + _salaryPlusBonus +
				'}';
	}
}