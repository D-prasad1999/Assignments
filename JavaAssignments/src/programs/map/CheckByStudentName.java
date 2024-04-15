package programs.map;

import java.util.Map;
import java.util.TreeMap;

public class CheckByStudentName {
	public static void main(String[] args) {
		Map<Integer, Student> studentList = new TreeMap<>();
	    studentList.put(1, new Student("sai", 20, 70));
	    studentList.put(2, new Student("ravi", 22, 80));
	    studentList.put(3, new Student("kiran", 21, 60));
	    studentList.put(4, new Student("hari", 23, 75));
	    studentList.put(5, new Student("sai", 24, 72));
	    
	    for (Map.Entry<Integer, Student> student : studentList.entrySet()) {
	        if (student.getValue().getName().equals("sai")) {
	            System.out.println(student.getKey() + " " + student.getValue());
	        }
	    }
	}
}
