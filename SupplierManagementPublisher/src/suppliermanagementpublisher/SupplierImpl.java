package suppliermanagementpublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import bookstoredbcontext.DbContextImpl;
import bookstoredbcontext.IDbContext;

public class SupplierImpl implements ISupplierService {
	

		private Connection connection = null; 
		private IDbContext  dbContext;
		private Statement statment;
		private ResultSet  resultSet;
		private static PreparedStatement preparedStatement = null;
		
		Scanner sc = new Scanner(System.in);
		
		public SupplierImpl() {
			super();
			
			this.dbContext = new DbContextImpl();
			this.connection = dbContext.getDatabaseConnection();
			
		}

		@Override
		public void saveSupplier() {
			
			Supplier supplier = new Supplier();
			
			System.out.println("Enter Your First Name :");
			supplier.setFirstName(sc.nextLine().trim());
			
			System.out.println("Enter Your Last Name :");
			supplier.setLastName(sc.nextLine().trim());
			
			System.out.println("Enter Your Email :");
			supplier.setEmail(sc.nextLine().trim());
			
			System.out.println("Enter Your NIC :");
			supplier.setNIC(sc.nextLine().trim());
			
			System.out.println("Enter Your Address :");
			supplier.setAddress(sc.nextLine().trim());
			
			System.out.println("Enter Your Mobile Number :");
			supplier.setMobileNumber(sc.nextLine().trim());
			
			System.out.println("Enter Your Company Name :");
			supplier.setCompanyName(sc.nextLine().trim());
			
			System.out.println("Enter Your Password :");
			supplier.setPassword(sc.nextLine().trim());
			
			try {
				
				String query = "INSERT INTO supplier VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?, '1')";
				
				preparedStatement = connection.prepareStatement(query); 
				
				preparedStatement.setString(1, supplier.getFirstName());
				preparedStatement.setString(2, supplier.getLastName());
				preparedStatement.setString(3, supplier.getEmail());
				preparedStatement.setString(4, supplier.getNIC());
				preparedStatement.setString(5, supplier.getAddress());
				preparedStatement.setString(6, supplier.getMobileNumber());
				preparedStatement.setString(7, supplier.getCompanyName());
				preparedStatement.setString(8, supplier.getPassword());
				
				int isSuccess = preparedStatement.executeUpdate();
				
				if(isSuccess > 0) {
					
					System.out.println("Supplier Registration Has Been Successfully ");
					
				}else {
					
					System.out.println("Error has been orccured please try again..");
					
				}
				
				
			}catch(Exception ex) {
				
				System.out.println("supplierSaveError : " + ex.getMessage());
			}
			
			

		}

	}
