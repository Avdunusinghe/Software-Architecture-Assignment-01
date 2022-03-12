package inventorymanagementpublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import bookstoredbcontext.DbContextImpl;
import bookstoredbcontext.IDbContext;

public class InventoryImpl implements IInventoryService {
	
	private Connection connection = null; 
	private IDbContext  dbContext;
	private Statement statment;
	private ResultSet  resultSet;
	private static PreparedStatement preparedStatement = null;
	
	
	
	public InventoryImpl() {
		super();
		
		this.dbContext = new DbContextImpl();
		this.connection = dbContext.getDatabaseConnection();
		
	}

	Scanner sc = new Scanner(System.in);

	@Override
	public void saveBooks() {
		
		
		Book book = new Book();
		
		System.out.println("Enter Book Name");
		book.setTitle(sc.nextLine().trim());
		
		System.out.println("Enter Book Author :");
		book.setAuthor(sc.nextLine().trim());
		
		System.out.println("Enter Book ISBN Number :");
		book.setIsbn(sc.nextInt());
		
		System.out.println("Enter Book Price:");
		book.setPrice(sc.nextInt());
		
		
		
		
		try {
			
			String query = "INSERT INTO book VALUES(0, ?, ?,?, ?,'1')";
			
			preparedStatement = connection.prepareStatement(query); 
			
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setInt(2, book.getIsbn());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setInt(4, book.getPrice());
			
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Book has been Successfully...");
				
			}else {
				
				System.out.println("Error has been occred please try again...");
				
			}	
			
		}catch(Exception ex) {
			
			System.out.println("bookServiceException:" + ex.getMessage());
		}
		
		
		

	}

}
