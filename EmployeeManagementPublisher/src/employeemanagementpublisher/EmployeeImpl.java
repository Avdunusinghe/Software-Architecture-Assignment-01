package employeemanagementpublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import bookstoredbcontext.DbContextImpl;
import bookstoredbcontext.IDbContext;


public class EmployeeImpl implements IEmployeeService {
	
	private Connection connection = null; 
	private IDbContext  dbContext;
	private Statement statment;
	private ResultSet  resultSet;
	private static PreparedStatement preparedStatement = null;
	
	
	
	public EmployeeImpl() {
		super();
		
		this.dbContext = new DbContextImpl();
		this.connection = dbContext.getDatabaseConnection();
	}


	Scanner sc = new Scanner(System.in);
	

	@Override
	public void saveEmployee() {
		
		Employee employee = new Employee();
		
		System.out.println("Enter Employee First Name :");
		employee.setFirstName(sc.nextLine().trim());
		
		System.out.println("Enter Employee Last Name :");
		employee.setLastName(sc.nextLine().trim());
		
		System.out.println("Enter Employee Email :");
		employee.setEmail(sc.nextLine().trim());
		
		System.out.println("Enter Employee Address :");
		employee.setAddress(sc.nextLine().trim());
		
		System.out.println("Enter Employee Mobile Number :");
		employee.setMobileNumber(sc.nextLine().trim());
		
		
		
		try {
			
			String query = "INSERT INTO user VALUES(0, ?, ?, ?, ?, ?, 'changeme', '1')";
			
			preparedStatement = connection.prepareStatement(query); 
			
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setString(5, employee.getMobileNumber());
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Employee Registration Has Been Successfully");
				
			}else {
				
				System.out.println("Error has been orccured please try again");
				
			}
			
			
		}catch(Exception ex) {
			
			System.out.println("employeeSaveError : " + ex.getMessage());
		}
		
		
		
	}

}
