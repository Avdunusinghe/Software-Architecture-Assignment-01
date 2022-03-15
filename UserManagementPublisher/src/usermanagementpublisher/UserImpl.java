package usermanagementpublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bookstoredbcontext.DbContextImpl;
import bookstoredbcontext.IDbContext;
import smtp.helper.EmailHelper;

public class UserImpl implements IUserService {
	
	private Connection connection = null; 
	private IDbContext  dbContext;
	private Statement statment;
	private ResultSet  resultSet;
	private static PreparedStatement preparedStatement = null;
	
	Scanner sc = new Scanner(System.in);
	
	public UserImpl() {
		super();
		
		this.dbContext = new DbContextImpl();
		this.connection = dbContext.getDatabaseConnection();
		
	}

	//@Save User
	@Override
	public void saveUser() {
		
		User user = new User();
		
		System.out.println("Enter Your First Name :");
		user.setFirstName(sc.nextLine().trim());
		
		System.out.println("Enter Your Last Name :");
		user.setLastName(sc.nextLine().trim());
		
		System.out.println("Enter Your Email :");
		user.setEmail(sc.nextLine().trim());
		
		System.out.println("Enter Your Address :");
		user.setAddress(sc.nextLine().trim());
		
		System.out.println("Enter Your Mobile Number :");
		user.setMobileNumber(sc.nextLine().trim());
		
		System.out.println("Enter Your Password :");
		user.setPassword(sc.nextLine().trim());
		
		try {
			
			String query = "INSERT INTO user VALUES(0, ?, ?, ?, ?, ?, ?, '1', '1')";
			
			preparedStatement = connection.prepareStatement(query); 
			
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getMobileNumber());
			preparedStatement.setString(6, user.getPassword());
			
			int isSuccess = preparedStatement.executeUpdate();
			
			EmailHelper emailHelper = new EmailHelper();
			
			emailHelper.sendRegisterCutomerEmail(user.getEmail(), "dev", "dev");
			
			if(isSuccess > 0) {
				
				System.out.println("User Registration Has Been Successfully");
				
			}else {
				
				System.out.println("Error has been orccured please try again");
				
			}
			
			
		}catch(Exception ex) {
			
			System.out.println("userSaveError : " + ex.getMessage());
		}
		
		

	}
	
	//@GET All User Details
	@Override
	public void getAllUsersDetails() {
		
		List<UserViewModel> vm = new ArrayList<>();
		
		try {
			
			String query = "SELECT id, firstName, lastName, email, address, mobileNumber FROM user WHERE isActive = 1 && roleId = 1";
			
			statment = connection.createStatement();
			resultSet = statment.executeQuery(query);
			
			System.out.println("\n==========================================User Details=================================================");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s %20s %20s\n", 
							"UserId", "First Name", "Last Name", "Email", "Address", "Mobile Number"
					)
			);
			
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %20s %20s %20s\n", 
						resultSet.getInt("id"),
						resultSet.getString("firstName"),
						resultSet.getString("lastName"),
						resultSet.getString("email"),
						resultSet.getString("address"),
						resultSet.getString("mobileNumber")
						
						
				);
				
				System.out.println("--------------------------------------------------------------------------------------------------------");
			}
			
			
			
		}catch(Exception ex) {
			
			System.out.println("getAllUsersDetailsException:" + ex.getMessage());
			
		}
		
		
	}
	
	//@DELETE User 
	
	@Override
	public void deleteUser() {
		
		Integer userId;
		
		System.out.print("\nPlease enter User id : ");
		userId = (sc.nextInt());
		
		try {
			
			String query = "UPDATE user SET isActive = 0";
			
			preparedStatement = connection.prepareStatement(query);
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Delete user has been successfully..");
				
			}else {
				
				System.out.println("Cannot find user..");
				
			}
			
		}catch (Exception ex) {
			
			System.out.println("userDeleteException : " + ex.getMessage());
			
		}
		
		
	}

	@Override
	public void saveEmployee() {
		
		EmployeeViewModel vm = new EmployeeViewModel();
		
		System.out.println("Enter Your First Name :");
		vm.setFirstName(sc.nextLine().trim());
		
		System.out.println("Enter Your Last Name :");
		vm.setLastName(sc.nextLine().trim());
		
		System.out.println("Enter Your Email :");
		vm.setEmail(sc.nextLine().trim());
		
		System.out.println("Enter Your Address :");
		vm.setAddress(sc.nextLine().trim());
		
		System.out.println("Enter Your Mobile Number :");
		vm.setMobileNumber(sc.nextLine().trim());
		
		
		try {
			
			String query = "INSERT INTO user VALUES(0, ?, ?, ?, ?, ?, 'changeme', '1', '2')";
			
			preparedStatement = connection.prepareStatement(query); 
			
			preparedStatement.setString(1, vm.getFirstName());
			preparedStatement.setString(2, vm.getLastName());
			preparedStatement.setString(3, vm.getEmail());
			preparedStatement.setString(4, vm.getAddress());
			preparedStatement.setString(5, vm.getMobileNumber());
			
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
