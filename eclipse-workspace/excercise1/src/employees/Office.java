package employees;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Office {
	
	
	private final String EMPLOYEE_FILE = "res/employees.txt";
	private final String STATISTICS_FILE = "res/statistics.txt";
	//Attributes
	private ArrayList<Employee> empList;
	Scanner input;
	
	//Constructors
	public Office() throws Exception {
		empList = new ArrayList<Employee>(); 
		makeEmployeeList();
		input = new Scanner(System.in);
	}
	
	//Operational Method
	public void printEmployees() {
		for(int i = 0; i<empList.size(); i++) {
			System.out.println(empList.get(i));
		}
		
	}
	
	public void addEmployee() {
		Employee newEmployee = new Employee();
		System.out.println("Please enter new employee information: ");
		
		System.out.print("Enter Employee Name: ");
		newEmployee.setName(input.next());
		
		System.out.print("Enter Employee ID: ");
		newEmployee.setId(input.nextInt());
		
		System.out.print("Enter Employee's working hours: ");
		newEmployee.setHours(input.nextDouble());
		
		System.out.print("Enter Employee's hourly wage: ");
		newEmployee.setHourlyWage(input.nextDouble());
		
		empList.add(newEmployee);
		
		System.out.println("\nNew employee is added!");	
	}
	
	public void saveEmployees() throws Exception {
		FileWriter fw = new FileWriter(EMPLOYEE_FILE, false);
		PrintWriter newFile = new PrintWriter(fw);
		for(int i = 0; i < empList.size(); i++) {
			newFile.print(empList.get(i).getName() + ";");
			newFile.print(empList.get(i).getId() + ";");
			newFile.print(empList.get(i).getHours() + ";");
			newFile.print(empList.get(i).getHourlyWage() + ";");
			newFile.println();
		}
		
		fw.close();
		newFile.close();
		
		System.out.println("Files Saved!");
	}
	
	public void removeEmployee() {
		int employeeId;
		System.out.print("Please enter the ID of the Employee you " +
							"want to remove: ");
		employeeId = input.nextInt();
		
		for(int i = 0; i < empList.size(); i++) {
			if(empList.get(i).getId() == employeeId) {
				empList.remove(i);
			}
		}
		
		System.out.println("\nThe Employee is removed!\n");
	}
	
	//Private Methods
	private void makeEmployeeList()throws Exception {
		File myFile = new File(EMPLOYEE_FILE);
		Scanner reader = new Scanner(myFile);
		String [] splittedLine;
		
		while(reader.hasNext()) {
			splittedLine = reader.nextLine().trim().split(";");
			Employee emp = new Employee(splittedLine[0], 
					Integer.parseInt(splittedLine[1]), 
					Double.parseDouble(splittedLine[2]), 
					Double.parseDouble(splittedLine[3]));
			empList.add(emp);
		}
		reader.close();
	}

}
