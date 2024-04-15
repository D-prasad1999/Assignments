package programs.set;



class Employees implements Comparable<Employees>{

	private int id;
	private String name;
	private int age;
	private String company;
	private double salary;
	
	public Employees(int id, String name, int age, String company, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.company = company;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public int compareTo(Employees emp) {
		return Integer.compare(this.id, emp.getId());
	}
	
	@Override
	public String toString() {
		return id+" "+name+" "+age+" "+company+" "+salary;
	}
	
}
