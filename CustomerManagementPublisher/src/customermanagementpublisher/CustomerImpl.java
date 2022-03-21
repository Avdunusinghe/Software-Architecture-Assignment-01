package customermanagementpublisher;

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

public class CustomerImpl implements ICustomerService {
	
	private Connection connection = null; 
	private IDbContext  dbContext;
	private Statement statement;
	private ResultSet  resultSet;
	private static PreparedStatement preparedStatement = null;
	
	Scanner sc = new Scanner(System.in);
	
	public CustomerImpl() {
		super();
		
		this.dbContext = new DbContextImpl();
		this.connection = dbContext.getDatabaseConnection();
		
	}

	@Override
	public void saveCustomer() {
		
		Customer customer = new Customer();
		
		System.out.println("Enter Your First Name :");
		customer.setFirstName(sc.nextLine().trim());
		
		System.out.println("Enter Your Last Name :");
		customer.setLastName(sc.nextLine().trim());
		
		System.out.println("Enter Your Email :");
		customer.setEmail(sc.nextLine().trim());
		
		System.out.println("Enter Your Address :");
		customer.setAddress(sc.nextLine().trim());
		
		System.out.println("Enter Your Mobile Number :");
		customer.setMobileNumber(sc.nextLine().trim());
		
		System.out.println("Enter Your Password :");
		customer.setPassword(sc.nextLine().trim());
		
		try {
			
			String query = "INSERT INTO user VALUES(0, ?, ?, ?, ?, ?, ?, '1', '1')";
			
			preparedStatement = connection.prepareStatement(query); 
			
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setString(4, customer.getAddress());
			preparedStatement.setString(5, customer.getMobileNumber());
			preparedStatement.setString(6, customer.getPassword());
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Registration Has Been Successfully");
				
			}else {
				
				System.out.println("Error has been orccured please try again");
				
			}
			
			
		}catch(Exception ex) {
			
			System.out.println("customerSaveError : " + ex.getMessage());
		}
		
		
	}

	@Override
	public void requestBook() {
		
		RequestBook requestBook = new RequestBook();
		
		System.out.println("Please enter book name");
		requestBook.setBooknName(sc.nextLine().trim());
		
		System.out.println("Please enter author name");
		requestBook.setAuthor(sc.nextLine().trim());
		
		System.out.println("Please enter messeage");
		requestBook.setMessage(sc.nextLine().trim());
		
		try {
			
			String query = "INSERT INTO requestbook VALUES(0, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, requestBook.getBooknName());
			preparedStatement.setString(2, requestBook.getAuthor());
			preparedStatement.setString(3, requestBook.getMessage());
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Request book has been successfully");
				
			}else {
				
				System.out.println("Error has been orccured please try again");
				
			}
			
			
		}catch (Exception ex) {
			
			System.out.println("requestBookError : " + ex.getMessage());
		}
		
	}
	
	@Override
	public void orderBook() {
		
		
		Order order = new Order();
		
		System.out.println("Please enter book name");
		order.setBookName(sc.nextLine().trim());
		
		System.out.println("Please enter your address");
		order.setAddress(sc.nextLine().trim());
		
		
		try {
			
			String query = "INSERT INTO orders VALUES(0, ?, ?, '1')";
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, order.getBookName());
			preparedStatement.setString(2, order.getAddress());
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println(" Order has been successfully");
				
			}else {
				
				System.out.println("Error has been orccured please try again");
				
			}
			
		}catch (Exception ex) {
			
			System.out.println("order : " + ex.getMessage());
			
		}
		
		
	}

	@Override
	public void getAllBookDetails() {
		
		try {
			
			String query = "SELECT id, title, isbn, author, price FROM book WHERE isActive = 1";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("\n==========================================Books Details=================================================");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s %20s\n", 
							"BookId", "Title", "Isbn Number", "Author", "price"
					)
			);
			
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %20s %20s\n", 
						resultSet.getInt("id"),
						resultSet.getString("title"),
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
	
	public void deleteCustomer() {
		Integer userId;
		
		System.out.print("\nPlease enter Customer id : ");
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
	
	public void getAllRequestBooks() {
		
		try {
			
			String query = "SELECT id, bookName, author, messeage FROM requestbook";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("\n==========================================Request Books Details=================================================");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s\n", 
							"Id", "Book Name", "Author", "Message"
					)
			);
			
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %20s\n", 
						resultSet.getInt("id"),
						resultSet.getString("bookName"),
						resultSet.getString("author"),
						resultSet.getString("messeage")
						
						
				);
				
				System.out.println("--------------------------------------------------------------------------------------------------------");
			}
			
			
			
		}catch(Exception ex) {
			
			System.out.println("getAllBookDetailsException:" + ex.getMessage());
			
		}
	}

	public void getRequestBooksreport() {
		try {
			
			String query = "SELECT id, bookName , author, messeage  FROM requestbook";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			File directory = new File("D:\\SA-OSGI\\Software-Architecture-Assignment-01");
			
			directory.mkdirs();
			
			File file = new File(directory,"RequestBooks.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write(String.format("=================================================== Request Book Details Report ============================================================================================\n\n"));
			fileWriter.write(
					
					String.format
					(
							"%25s %25s %25s %25s\n", 
							"Book Id", "Book Name", "Author", "Message"
					)
			);
			
			fileWriter.write(String.format("===================================================================================================================================================================================\n\n"));
			
			while(resultSet.next()) {
				
				fileWriter.write(
						
						String.format(
								
								"%20d %25s %25s %25s\n", 
								resultSet.getInt("id"),
								resultSet.getString("bookName"),
								resultSet.getString("author"),
								resultSet.getString("messeage")
								
						)
				);
				
				fileWriter.write(String.format("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"));
			}
			
			fileWriter.flush();
			fileWriter.close();
			
			
			System.out.println("Request Book Report genaration has been successfully");
				
			
		}catch (Exception ex) {
			
			System.out.println("requestBookDetailsReportException:" + ex.getMessage());
			
			
		}
		
	}

	public void getOrderBooksreport() {
		try {
			
			String query = "SELECT id, bookName , address FROM orders";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			File directory = new File("D:\\SA-OSGI\\Software-Architecture-Assignment-01");
			
			directory.mkdirs();
			
			File file = new File(directory,"OrderBooks.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write(String.format("=================================================== Order Book Details Report ============================================================================================\n\n"));
			fileWriter.write(
					
					String.format
					(
							"%25s %25s %25s\n", 
							"Book Id", "Book Name", "Address"
					)
			);
			
			fileWriter.write(String.format("===================================================================================================================================================================================\n\n"));
			
			while(resultSet.next()) {
				
				fileWriter.write(
						
						String.format(
								
								"%20d %25s %25s\n", 
								resultSet.getInt("id"),
								resultSet.getString("bookName"),
								resultSet.getString("address")
								
						)
				);
				
				fileWriter.write(String.format("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"));
			}
			
			fileWriter.flush();
			fileWriter.close();
			
			
			System.out.println("Order Book Report genaration has been successfully");
				
			
		}catch (Exception ex) {
			
			System.out.println("orderBookDetailsReportException:" + ex.getMessage());
			
			
		}
	}
}