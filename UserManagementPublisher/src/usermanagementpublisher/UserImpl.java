package usermanagementpublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import bookstoredbcontext.DbContextImpl;
import bookstoredbcontext.IDbContext;

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
			
			String query = "INSERT INTO user VALUES(0, ?, ?, ?, ?, ?, ?, '1')";
			
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

}
