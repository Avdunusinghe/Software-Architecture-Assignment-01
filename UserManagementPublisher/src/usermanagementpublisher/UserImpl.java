package usermanagementpublisher;

import java.sql.Connection;
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
	
	Scanner sc = new Scanner(System.in);
	
	public UserImpl(Connection connection, IDbContext dbContext) {
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
		
		try {
			
			String query = "INSERT INTO user(firstName, lastName, email, mobileNumber)" +
						"VALUES('"+ user.getFirstName() + "','" + user.getLastName() + "', '" + user.getEmail() + "','" + user.getAddress() + "', '" + user.getMobileNumber() + "')";
			
			statment = connection.createStatement();
			statment.executeUpdate(query);
			
			System.out.println("User Registration Successfully");
			
		}catch(Exception ex) {
			
			System.out.println("userSaveError : " + ex.getMessage());
		}
		
		

	}

}
