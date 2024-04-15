package programs.set;

import java.util.Set;
import java.util.TreeSet;

public class SortEmployeeByAge {
	public static void main(String[] args) {
		Set<Employees> employeeList=new TreeSet<>();
		employeeList.add(new Employees(3, "sai", 23, "CG", 23000));
		employeeList.add(new Employees(1, "ram", 21, "CG", 21000));
		employeeList.add(new Employees(5, "ravi", 26, "CG", 26000));
		employeeList.add(new Employees(2, "kumar", 23, "CG", 22000));
		employeeList.add(new Employees(4, "hari", 25, "CG", 23000));
		
		for(Employees employee:employeeList) {
			System.out.println(employee);
		}
	}
}
