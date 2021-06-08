package com.cognixia.jump.projects;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.streams.Employee;

public class EmployeeManagementSystem {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		ArrayList<Employee> employees = new ArrayList<>();
		 
		// if already objects in file, put it in the array before we perform any operations
		File file = new File("resources/employeeList.data");
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			//Employee employee;
			
			employees = (ArrayList<Employee>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// Main Loop
		do {
			System.out.println("==========MENU===========\n"
							 + "1. Add new employee\n"
							 + "2. Update current employee\n"
							 + "3. Remove current employee\n"
							 + "4. List employee information\n");
			try {
				choice = input.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				switch(choice) {
				case 1: 
					System.out.println("---Enter employee info---");
					addEmployee(file, employees, input);
					break;
				case 2:
					System.out.println("---Update employee info---");
					updateEmployee(file, employees, input);
					break;
				case 3:
					System.out.println("---Remove employee---");
					deleteEmployee(file, employees, input);
					break;
				case 4:
					System.out.println("---Employee List---");
					printEmployees(file);
					break;
				default: System.out.println("Please enter a valid number!");
						
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Would you like to do something else? (Enter 1 for more. Enter 0 to finish) ");
			try {
				choice = input.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while (choice > 0);
		
		System.out.println("End of application.\n");
		input.close();

	}
	// Get user input, create Employee object based on input, write into data file
	public static void addEmployee(File file, ArrayList<Employee> arr, Scanner input) throws IOException, InvalidSalaryException, Exception {
		// Get employee info
		System.out.println("Enter employee first name: ");
		String firstName = input.next();
		
		System.out.println("Enter employee last name: ");
		String lastName = input.next();
		
		System.out.println("Enter date employed(mm/dd/yyyy): ");
		String dateEmployed = input.next();
		
		System.out.println("Enter employee salary: ");
		int salary = input.nextInt();
		if(salary < 0) throw new InvalidSalaryException();
		
		System.out.println("Enter employee department: ");
		String department = input.next();
		
		
		System.out.println("Employee entered.");
		
	
		// Add employee into file
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("File created successfully.");
			} catch (IOException e) {
				System.out.println("File could not be created, see stack trace below.");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream fop = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fop);
			
		    //add the employee to the arraylist, then add arraylist into file
			arr.add(new Employee(firstName, lastName, dateEmployed, salary, department));

			oos.writeObject(arr);
			oos.writeObject(null);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Select Employee index you would like to change, change it, then pass the updated list into file
	public static void updateEmployee(File file, ArrayList<Employee> arr, Scanner input) throws InvalidSalaryException, Exception {
		for(Employee e : arr) System.out.println(e);
		System.out.println("\nWhose info would you like to change? (Index from the top 0-" + (arr.size() - 1) + ")");
		int choice = input.nextInt();
		
		do {
			System.out.println("What would you like to change?\n" 
					+  "1. First Name\n"
					+  "2. Last Name\n"
					+  "3. Date Employed\n"
					+  "4. Salary\n"
					+  "5. Department");
			int switchCase = input.nextInt();
			
			switch(switchCase) {
			case 1:
				System.out.println("Enter new first name: ");
				String firstName = input.next();
				arr.get(choice).setFirstName(firstName);
				System.out.println("Updated! Go back to Menu and print to view.");
				break;
			case 2:
				System.out.println("Enter new last name: ");
				String lastName = input.next();
				arr.get(choice).setLastName(lastName);
				System.out.println("Updated! Go back to Menu and print to view.");
				break;
			case 3:
				System.out.println("Enter new employment date: ");
				String dateEmployed = input.next();
				arr.get(choice).setDateOfEmployment(dateEmployed);
				System.out.println("Updated! Go back to Menu and print to view.");
				break;
			case 4:
				System.out.println("Enter new salary: ");
				int salary = input.nextInt();
				if(salary < 0) throw new InvalidSalaryException();
				arr.get(choice).setSalary(salary);
				System.out.println("Updated! Go back to Menu and print to view.");
				break;
			case 5:
				System.out.println("Enter new department: ");
				String department = input.next();
				arr.get(choice).setDepartment(department);
				System.out.println("Updated! Go back to Menu and print to view.");
				break;
			default:
				System.out.println("Not a valid number!\n");
			}
			System.out.println("Would you change anything else for " + arr.get(choice).getFirstName() + "? (Enter 1 for more. Enter 0 to finish) ");
			choice = input.nextInt();
			
		} while(choice > 0);
		// send the change into data
		fileHelper(file, arr);
		
	}
	// Select an index to delete
	public static void deleteEmployee(File file, ArrayList<Employee> arr, Scanner input) throws Exception{
		for(Employee e : arr) System.out.println(e);
		System.out.println("\nWhose info would you like to delete? (Index from the top 0-" + (arr.size() - 1) + ")");
		int choice = input.nextInt();
		
		arr.remove(choice);
		
		fileHelper(file, arr);
	}
	// Gets list of Employee objects from data file and prints it
	public static void printEmployees(File file) throws Exception {
		ArrayList<Object> objectsList = new ArrayList<>();
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object employee;
			
		while((employee = ois.readObject()) != null) {
			objectsList.add(employee);
		}
		ois.close();
		
		for(Object obj : objectsList) {
			System.out.println(obj);
		}
	}
	// inputs ArrayList of Employees into data file
	public static void fileHelper(File file, ArrayList<Employee> arr) throws Exception {
		FileOutputStream fop = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fop);
			
		oos.writeObject(arr);
		oos.writeObject(null);
		oos.close();
	}
}
