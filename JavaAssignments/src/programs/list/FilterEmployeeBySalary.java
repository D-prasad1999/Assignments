package programs.list;

import java.util.ArrayList;
import java.util.List;

public class FilterEmployeeBySalary {
	public static void main(String[] args) {
		List<Employee> employeeList=new ArrayList<>();
		employeeList.add(new Employee(1, "sai", 23, "CG", 20000));
		employeeList.add(new Employee(2, "ram", 25, "CG", 22000));
		employeeList.add(new Employee(3, "hari", 21, "CG", 18000));
		employeeList.add(new Employee(4, "ravi", 23, "CG", 24000));
		employeeList.add(new Employee(5, "kiran", 24, "CG", 21000));
		
		for(Employee employee:employeeList) {
			if(employee.getSalary()<22000) {
				System.out.println(employee);
			}
		}
	}
}
