package usermanagementpublisher;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bookstoredbcontext.DbContextImpl;
import bookstoredbcontext.IDbContext;
import common.viewmodel.ResponseViewModel;

public class UserImpl implements IUserService {
	
	private Connection connection = null; 
	private IDbContext  dbContext;
	private Statement statement;
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
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("\n==========================================User Details===============================================================================");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s %20s %20s\n", 
							"UserId", "First Name", "Last Name", "Email", "Address", "Mobile Number"
					)
			);
			
			System.out.println("======================================================================================================================================");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %24s %20s %20s\n", 
						resultSet.getInt("id"),
						resultSet.getString("firstName"),
						resultSet.getString("lastName"),
						resultSet.getString("email"),
						resultSet.getString("address"),
						resultSet.getString("mobileNumber")
						
						
				);
				
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
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
			
			String query = "UPDATE user SET isActive = 0 WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1,userId);
			
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
   
	//@SAVE Employee
	@Override
	public void saveEmployee() {
		
		EmployeeViewModel vm = new EmployeeViewModel();
		
		System.out.println("Enter Employee First Name :");
		vm.setFirstName(sc.nextLine().trim());
		
		System.out.println("Enter Employee Last Name :");
		vm.setLastName(sc.nextLine().trim());
		
		System.out.println("Enter Employee Email :");
		vm.setEmail(sc.nextLine().trim());
		
		System.out.println("Enter Employee Address :");
		vm.setAddress(sc.nextLine().trim());
		
		System.out.println("Enter Employee Mobile Number :");
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
    
	//@GENARATE ReportBy RoleId
	@Override
	public void genarateUserDetailsReportByRoleId() {
		
		try {
			
			System.out.println("=================================================================");
			System.out.println("Customer Role Id => 1  :");
			System.out.println("Employee Role Id => 2  :");
			System.out.println("Please Enter User Role Id");
			
			int roleId = sc.nextInt();
						
			String  query = "SELECT id, firstName, lastName, email, address, mobileNumber FROM user WHERE isActive = 1 && roleId = ?";
			  
	
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, roleId);
			resultSet = preparedStatement.executeQuery();
			
			File directory = new File("C:\\OnlineBookStore\\User");
			
			directory.mkdirs();
			
			File file = new File(directory,"userList.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			if(roleId == 1) {
				
				fileWriter.write(String.format("================================================= User Details Report ============================================================\n"));
			}else {
				fileWriter.write(String.format("================================================= Employee Details Report ============================================================\n"));
			}
			
			fileWriter.write(
					
					String.format
					(
							"%20s %20s %20s %20s %20s %20s\n", 
							"UserId", "First Name", "Last Name", "Email", "Address", "Mobile Number"
					)
			);
			
			fileWriter.write(String.format("=========================================================================================================================================\n"));
			
			while(resultSet.next()) {
				
				fileWriter.write(
						
						String.format(
								
								"%20d %20s %20s %24s %20s %20s\n", 
								resultSet.getInt("id"),
								resultSet.getString("firstName"),
								resultSet.getString("lastName"),
								resultSet.getString("email"),
								resultSet.getString("address"),
								resultSet.getString("mobileNumber")
								
						)
				);
				
				fileWriter.write(String.format("-----------------------------------------------------------------------------------------------------------------------------------\n"));
			}
			
			fileWriter.flush();
			fileWriter.close();
			
			
			System.out.println("Report genaration has been successfully");
				
			
		}catch (Exception ex) {
			
			System.out.println("genarateUserDetailsReportException:" + ex.getMessage());
			
			
		}
		
	}

	@Override
	public void getDeletedUserDetails() {
		
		try {
			
			
			
			System.out.println("=================================================================");
			System.out.println("Customer Role Id => 1  :");
			System.out.println("Employee Role Id => 2  :");
			System.out.println("Please Enter User Role Id");
			
			int roleId = sc.nextInt();
						
			String  query = "SELECT id, firstName, lastName, email, address, mobileNumber FROM user WHERE isActive = 0 && roleId = ?";
			
			
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, roleId);
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("\n==========================================User Details===============================================================================");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s %20s %20s\n", 
							"UserId", "First Name", "Last Name", "Email", "Address", "Mobile Number"
					)
			);
			
			System.out.println("======================================================================================================================================");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %24s %20s %20s\n", 
						resultSet.getInt("id"),
						resultSet.getString("firstName"),
						resultSet.getString("lastName"),
						resultSet.getString("email"),
						resultSet.getString("address"),
						resultSet.getString("mobileNumber")
						
						
				);
				
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			}
			
			System.out.println("=========================================================================================================================================");
			
			System.out.println("if you want to genarate Report All Deleted users (Y/N)");
			Scanner choice = new Scanner(System.in);
			String  genarateReportChoice = choice.nextLine().trim().toLowerCase();
			
			if(genarateReportChoice.equals("y")) {
				
				ResponseViewModel response = new ResponseViewModel();
				
				response = genarateReportDeletedUsers(query,roleId);
				
				if(response.isSuccess) {
					
					System.out.println(response.getMessage());
					
				}
				
				
			}else {
				
				System.out.println("======================================================================================================================================================");
			}
			
		}catch(Exception ex) {
			
			System.out.println("getAllUsersDetailsException:" + ex.getMessage());
			
		}
			
		
		
	}
	
	private ResponseViewModel genarateReportDeletedUsers(String query,int roleId) {
		
		ResponseViewModel response = new ResponseViewModel();
		
		try {  
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, roleId);
			resultSet = preparedStatement.executeQuery();
			
			File directory = new File("C:\\OnlineBookStore\\User");
			
			directory.mkdirs();
			
			File file = new File(directory,"deletedUserList.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			if(roleId == 1) {
				
				fileWriter.write(String.format("================================================= User Details Report ============================================================\n"));
			}else {
				fileWriter.write(String.format("================================================= Employee Details Report ============================================================\n"));
			}
			
			fileWriter.write(
					
					String.format
					(
							"%20s %20s %20s %20s %20s %20s\n", 
							"UserId", "First Name", "Last Name", "Email", "Address", "Mobile Number"
					)
			);
			
			fileWriter.write(String.format("=========================================================================================================================================\n"));
			
			while(resultSet.next()) {
				
				fileWriter.write(
						
						String.format(
								
								"%20d %20s %20s %24s %20s %20s\n", 
								resultSet.getInt("id"),
								resultSet.getString("firstName"),
								resultSet.getString("lastName"),
								resultSet.getString("email"),
								resultSet.getString("address"),
								resultSet.getString("mobileNumber")
								
						)
				);
				
				fileWriter.write(String.format("-----------------------------------------------------------------------------------------------------------------------------------\n"));
			}
			
			fileWriter.flush();
			fileWriter.close();
			
			response.setMessage("Report genaration has been successfully");
			response.setSuccess(true);
			
			
		}catch(Exception ex) {
			
			response.setMessage("Error");
			response.setSuccess(false);
		}
		
		return response;
	}

}
