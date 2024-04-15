package programs.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterEmployeeBySalaryUsingStreams {
	public static void main(String[] args) {
		List<Employee> employeeList=new ArrayList<>();
		employeeList.add(new Employee(1, "sai", 23, "CG", 20000));
		employeeList.add(new Employee(2, "ram", 25, "CG", 22000));
		employeeList.add(new Employee(3, "hari", 21, "CG", 18000));
		employeeList.add(new Employee(4, "ravi", 23, "CG", 24000));
		employeeList.add(new Employee(5, "kiran", 24, "CG", 21000));
		
		List<Employee> employees=employeeList.stream().filter(emp->emp.getSalary()>22000)
				.collect(Collectors.toList());
		employees.forEach(student -> System.out.println(student));
	}
}
