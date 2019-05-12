package employeeManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import employeeDomain.Employee;
import employeeDomain.Salary;
import employeeDomain.Wage;

public class Manager {
	
	private final String EMPLOYEE_FILE = "res/employees.txt";
	private ArrayList<Employee> empList;
	private Scanner scanFile;
	private boolean valid = false;
	Scanner keyboard = new Scanner(System.in);
	
	public Manager() throws Exception 
	{
		empList = new ArrayList<Employee>();
		loadEmployees();
	}
	
	private void loadEmployees() throws Exception
	{
		//open and read file
		File inputFile = new File(EMPLOYEE_FILE);
		scanFile = new Scanner(inputFile);
		String [] splitted;
		

		while(scanFile.hasNext()) 
		{
			splitted = scanFile.nextLine().split(":");
			
			switch(splitted[0].charAt(0)) 
			{
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
					Salary s = new Salary (Integer.parseInt(splitted[0]), 
											splitted[1], 
											splitted[2],
											splitted[3],
											Long.parseLong(splitted[4]),
											splitted[5], 
											splitted[6], 
											Double.parseDouble(splitted[7]));
					empList.add(s);
					break;
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					Wage w = new Wage (Integer.parseInt(splitted[0]), 
							splitted[1], 
							splitted[2],
							splitted[3],
							Long.parseLong(splitted[4]),
							splitted[5], 
							splitted[6], 
							Double.parseDouble(splitted[7]),
							Double.parseDouble(splitted[8]));
			empList.add(w);
			break;
							
			}
				

		}
		
	}
	
	public void displayEmployees() 
	{
		for(Employee e: empList) 
		{
			System.out.println(e);
		}
	}
	
	private boolean isValid (int id) 
	{
		for(Employee emp: empList) 
		{
			if(emp.getId() == id) 
			{
				valid = false;
				break;
			}
			else 
			{
				valid = true;
			}
		}
		
		return valid;
	}
	
	public void addEmployee() throws Exception
	{
		int newId;
	   System.out.println("Welcome to the Add Employee Wizard! Please enter the following informatin.");
			do 
			{
				System.out.print("Enter ID: ");
				newId = keyboard.nextInt();
				isValid(newId);
			} while(valid == false);
			
			keyboard.nextLine();

			System.out.print("Enter Name: ");
			String newName = keyboard.nextLine();
					
			System.out.print("Enter Address:");
			String newAddress = keyboard.nextLine();
					
			System.out.print("Enter Phone: ");
			String newPhone = keyboard.nextLine();
					
					
			System.out.print("Enter DOB: ");
			String newDob = keyboard.nextLine();

					
			System.out.print("Enter Dept: ");
			String newDept = keyboard.nextLine();
			
			System.out.print("Enter SIN: ");
			Long newSin = keyboard.nextLong();
			
			if(Integer.toString(newId).charAt(0) == '0' || 
					Integer.toString(newId).charAt(0)== '1' ||
					Integer.toString(newId).charAt(0) == '2' ||
					Integer.toString(newId).charAt(0) == '3' ||
					Integer.toString(newId).charAt(0) == '4')
			{
				System.out.print("Enter Salary: ");
				double newSalary = keyboard.nextDouble();
				Salary newSalEmp = new Salary(newId, newName, newAddress, 
						newPhone, newSin, newDob, newDept, newSalary);
				empList.add(newSalEmp);
			}
			else 
			{
				System.out.print("Enter Hours: ");
				double newHours = keyboard.nextDouble();
				
				System.out.print("Enter Rate:");
				double newRate = keyboard.nextDouble();
				
				Wage newWagEmp = new Wage(newId, newName, newAddress, 
						newPhone, newSin, newDob, newDept, newHours, newRate);
				empList.add(newWagEmp);
			}
			
		updateEmpFile();
		System.out.print("New Employee had been added");	
	}
	
	public void removeEmployee() throws Exception
	{
		boolean retry = true;
		String answer = "";
		String answerTwo = "";
		
		System.out.println("Welcome to the remove an employee Wizard!");
		do {
			System.out.print("Enter the employee id to remove: ");
			int idForRemoval = keyboard.nextInt();
		
			for(int i = 0; i < empList.size(); i++) 
			{
				if(empList.get(i).getId() == idForRemoval) 
				{
					System.out.print("Are you trying to remove " + 
										empList.get(i).getName() + "? (Yes/No): ");
					answer = keyboard.next();
				}
			
				if(answer.equalsIgnoreCase("Yes")) 
				{
					empList.remove(i);
					System.out.print("Employee removed!");
					retry = false;
					updateEmpFile();
				}
			}
			
			if(retry==true) 
			{
				System.out.print("Incorrect Id entered. Would you like to try again? (Yes/No): ");
				answerTwo = keyboard.next();
				
				if(answerTwo.equalsIgnoreCase("No")) 
				{
					retry = false;
				}
				
			}
		}while(retry == true);	

	}
	
	private void updateEmpFile () throws Exception
	{
		FileWriter openEmpFile = new FileWriter(EMPLOYEE_FILE, false);
		PrintWriter updateFile = new PrintWriter(openEmpFile);
		
		for(Employee emp: empList) 
		{
			updateFile.print(emp.getId() + ":" + 
							emp.getName() + ":" +
							emp.getAddress() + ":" + 
							emp.getPhone() + ":" + 
							emp.getSin() + ":" + 
							emp.getDob() + ":" + 
							emp.getDept() + ":");
			
			if(emp instanceof Salary) 
			{
				updateFile.print(((Salary) emp).getSalary());
			}
			else if(emp instanceof Wage) 
			{
				updateFile.print(((Wage) emp).getRate() + ":");
				updateFile.print(((Wage) emp).getHours());
			}
			updateFile.println();
		}
		updateFile.close();	
	}

}
