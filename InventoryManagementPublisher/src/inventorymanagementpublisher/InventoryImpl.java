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

	@Override
	public void deleteBook() {
		
		Integer bookId;
		
		System.out.print("\nPlease enter Book id : ");
		bookId = (sc.nextInt());
		
		try {
			
			String query = "UPDATE book SET isActive = 0";
			
			preparedStatement = connection.prepareStatement(query);
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Book has been delete successfully..");
				
			}else {
				
				System.out.println("Cannot find book..");
				
			}
			
		}catch (Exception ex) {
			
			System.out.println("userDeleteException : " + ex.getMessage());
			
		}
		
	}

	@Override
	public void getAllBooksDetails() {
		
		try {
			
			String query = "SELECT id, title, isbn, author, price FROM book WHERE isActive = 1";
			
			statment = connection.createStatement();
			resultSet = statment.executeQuery(query);
			
			System.out.println("\n==========================================Books Details=================================================");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s\n", 
							"BookId", "Isbn Number", "Author", "price"
					)
			);
			
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %20s %20s\n", 
						resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("lastName"),
						resultSet.getString("isbn"),
						resultSet.getString("author"),
						resultSet.getString("price")
						
						
				);
				
				System.out.println("--------------------------------------------------------------------------------------------------------");
			}
			
			
			
		}catch(Exception ex) {
			
			System.out.println("getAllBookDetailsException:" + ex.getMessage());
			
		}
		
	}

	@Override
	public void getBookById() {
		
		Integer bookId;
		
		System.out.println("Enter Book Id : ");
		bookId = (sc.nextInt());
		
		String query = "SELECT * FROM book WHERE id = '"+ bookId +"' && isActive = 1";
		
		try {
			
			statment = connection.createStatement();
			resultSet = statment.executeQuery(query);
			
			while (resultSet.next()) {  
				
		    	  System.out.printf("%20d %20s %20d %20s %20d\n",resultSet.getInt("id"),resultSet.getString("title"),resultSet.getInt("isbn"),resultSet.getString("author"), resultSet.getInt("price"));		    	
		      }		    	

		} catch (Exception ex) {
			
			System.out.println("Error has been orccured please try again");
			System.out.println(ex.getMessage());
			
		}
		
	}
	

}
