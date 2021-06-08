package com.cognixia.jump.streams;

/**
 * Class Purpose - utility class (no main method)
 * 		used to stream Employees and the attributes that describe an Employee object
 * 
 * Make the attributes, getters and setters, and override the .toString() method
 * 
 * Can file / code to class, no need to live code.
 */

public class Employee implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int idCounter = 1;

	private int id;
	private String firstName;
	private String lastName;
	private String dateEmployed;
	private int salary;
	private String department;

	public Employee(String firstName, String lastName, String dateEmployed, int salary, String department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateEmployed = dateEmployed;
		this.salary = salary;
		this.department = department;
		this.id = idCounter++;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 
	public String getDateOfEmployment(String dateEmployed) {
		return dateEmployed;
	}
	public void setDateOfEmployment(String dateEmployed) {
		this.dateEmployed = dateEmployed;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + firstName + " " + lastName + ", date employed=" + dateEmployed +  ", salary=" + salary + ", department=" + department + "]";
	}

}
