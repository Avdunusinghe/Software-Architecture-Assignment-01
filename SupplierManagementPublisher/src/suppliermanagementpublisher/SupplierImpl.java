package suppliermanagementpublisher;

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
import suppliermanagementpublisher.SupplierViewModel;

public class SupplierImpl implements ISupplierService {
	

		private Connection connection = null; 
		private IDbContext  dbContext;
		private Statement statement;
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
			supplier.setNic(sc.nextLine().trim());
			
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
				preparedStatement.setString(4, supplier.getNic());
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

		@Override
		public void getAllSupplierDetails() {
			
			List<SupplierViewModel> vm = new ArrayList<>();
			
			try {
				
				String query = "SELECT id, firstName, lastName, email, nic, address, mobileNumber , companyName FROM supplier WHERE isActive = 1 ";
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				
				System.out.println("\n======================================================================== Supplier  Details ==================================================================================\n");
				System.out.println
				(
						String.format
						(
								"%20s %20s %20s %25s %20s %20s %20s %20s\n", 
								"SupplierId", "First Name", "Last Name", "Email", "NIC" , "Address", "Mobile Number", "CompanyName"
						)
				);
				
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				
				
				while(resultSet.next()) {
					
					System.out.printf
					(
							"%20d %20s %20s %30s %20s %20s %20s %20s\n", 
							resultSet.getInt("id"),
							resultSet.getString("firstName"),
							resultSet.getString("lastName"),
							resultSet.getString("email"),
							resultSet.getString("nic"),
							resultSet.getString("address"),
							resultSet.getString("mobileNumber"),
							resultSet.getString("companyName")
							
							
					);
					
					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				}
				
				
				
			}catch(Exception ex) {
				
				System.out.println("getAllSupplierDetailsException:" + ex.getMessage());
				
			}
			
				
			
		}

		@Override
		public void deleteSupplier() {
			
			Integer supplierId;
			
			System.out.print("\nPlease enter Supplier id : ");
			supplierId = (sc.nextInt());
			
			try {
				
				String query = "UPDATE supplier SET isActive = 0 WHERE id = ?";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, supplierId);
				int isSuccess = preparedStatement.executeUpdate();
				
				if(isSuccess > 0) {
					
					System.out.println("Delete supplier has been successfully..");
					
				}else {
					
					System.out.println("Cannot find supplier..");
					
				}
				
			}catch (Exception ex) {
				
				System.out.println("supplierDeleteException : " + ex.getMessage());
				
			}
			
		}

		@Override
		public void getSupplierById() {
			
			Integer supplierId;
			
			System.out.println("Enter Supplier Id : ");
			supplierId = (sc.nextInt());
			
			String query = "SELECT * FROM supplier WHERE id = '"+ supplierId +"' && isActive = 1";
			
			try {
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				
				while (resultSet.next()) {  
					
					System.out.printf
					(
							"%20d %20s %20s %30s %20s %20s %20s %20s\n", 
							resultSet.getInt("id"),
							resultSet.getString("firstName"),
							resultSet.getString("lastName"),
							resultSet.getString("email"),
							resultSet.getString("nic"),
							resultSet.getString("address"),
							resultSet.getString("mobileNumber"),
							resultSet.getString("companyName")
							
							
					);
				}

			} catch (Exception ex) {
				
				System.out.println("Error has been occured please try again");
				System.out.println(ex.getMessage());
				
			}
			
		}

		@Override
		public void wholesaleOrder() {
			
			WholesaleOrder wholesaleorder = new WholesaleOrder();
			
			System.out.println("Please enter Number of Books");
			wholesaleorder.setNoOfBooks(sc.nextLine().trim());
			
			System.out.println("Please enter book name");
			wholesaleorder.setBookName(sc.nextLine().trim());
			
			System.out.println("Please enter Company name");
			wholesaleorder.setSupplierCompany(sc.nextLine().trim());
			
		
			
			
			try {
				
				String query = "INSERT INTO wholesaleorder VALUES(0, ?, ?, ?, '1')";
				
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setString(1, wholesaleorder.getNoOfBooks());
				preparedStatement.setString(2, wholesaleorder.getBookName());
				preparedStatement.setString(3, wholesaleorder.getSupplierCompany());
				
				
				int isSuccess = preparedStatement.executeUpdate();
				
				if(isSuccess > 0) {
					
					System.out.println(" WholeSaleOrder has been successfully");
					
				}else {
					
					System.out.println("Error has been orccured please try again");
					
				}
				
			}catch (Exception ex) {
				
				System.out.println("wholesaleorder : " + ex.getMessage());
				
			}
			
			
		}

		@Override
		public void getAllWholeSaleOrders() {
			
			
			
			try {
				
				String query = "SELECT id, noOfBooks , bookName , supplierCompany  FROM wholesaleorder WHERE isActive = 1 ";
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				
				System.out.println("\n===================================================================== WholeSale Order  Details ==================================================================================\n");
				System.out.println
				(
						String.format
						(
								"%20s %20s %20s %25s\n", 
								"WholesaleOrderId", "No Of Books", "Book Name", "Supplier Company"
						)
				);
				
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				
				
				while(resultSet.next()) {
					
					System.out.printf
					(
							
							"%20d %20s %20s %25s\n", 
							resultSet.getInt("id"),
							resultSet.getString("noOfBooks"),
							resultSet.getString("bookName"),
							resultSet.getString("supplierCompany")
							
							
							
					);
					
					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				}
				
				
				
			}catch(Exception ex) {
				
				System.out.println("getAllWholesaleOrderDetailsException:" + ex.getMessage());
				
			}
			
			
		}

		@Override
		public void deleteWholesaleOrder() {
			
			Integer wholesaleorderId;
			
			System.out.print("\nPlease enter WholeSale Order id : ");
			wholesaleorderId = (sc.nextInt());
			
			try {
				
				String query = "UPDATE wholesaleorder SET isActive = 0 WHERE id = ?";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, wholesaleorderId);
				
				int isSuccess = preparedStatement.executeUpdate();
				
				if(isSuccess > 0) {
					
					System.out.println("Delete wholesale order has been successfully..");
					
				}else {
					
					System.out.println("Cannot find wholesale order..");
					
				}
				
			}catch (Exception ex) {
				
				System.out.println("wholesaleOrderDeleteException : " + ex.getMessage());
				
			}
			
		}

		@Override
		public void getWholesaleOrderById() {

			
			Integer wholesaleorderId;
			
			System.out.println("Enter WholeSaleOrder Id : ");
			wholesaleorderId = (sc.nextInt());
			
			String query = "SELECT * FROM wholesaleorder WHERE id = '"+ wholesaleorderId +"' && isActive = 1";
			
			try {
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				
				while (resultSet.next()) {  
					
					System.out.printf
					(
							
							"%20d %20s %20s %25s\n", 
							resultSet.getInt("id"),
							resultSet.getString("noOfBooks"),
							resultSet.getString("bookName"),
							resultSet.getString("supplierCompany")
							
							
							
					);
				}

			} catch (Exception ex) {
				
				System.out.println("Error has been occured please try again");
				System.out.println(ex.getMessage());
				
			}
			
		}

		@Override
		public void genarateSupplierDetailsReport() {
			
			try {
				
				String query = "SELECT id, firstName, lastName, email, nic, address, mobileNumber , companyName FROM supplier WHERE isActive = 1 ";
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				
				File directory = new File("F:\\SLIIT\\PROJECTS\\SA\\REPORT-GENERATE\\Supplier");
				
				directory.mkdirs();
				
				File file = new File(directory,"supplierList.txt");
				FileWriter fileWriter = new FileWriter(file);
				
				fileWriter.write(String.format("=========================================================== Supplier Details Report ============================================================================================\n"));
				fileWriter.write(
						
						String.format
						(
								"%20s %20s %20s %25s %20s %20s %20s %20s\n", 
								"SupplierId", "First Name", "Last Name", "Email", "NIC" , "Address", "Mobile Number", "CompanyName"
						)
				);
				
				fileWriter.write(String.format("===================================================================================================================================================================================\n"));
				
				while(resultSet.next()) {
					
					fileWriter.write(
							
							String.format(
									
									"%20d %20s %20s %30s %20s %20s %20s %20s\n", 
									resultSet.getInt("id"),
									resultSet.getString("firstName"),
									resultSet.getString("lastName"),
									resultSet.getString("email"),
									resultSet.getString("nic"),
									resultSet.getString("address"),
									resultSet.getString("mobileNumber"),
									resultSet.getString("companyName")
									
							)
					);
					
					fileWriter.write(String.format("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"));
				}
				
				fileWriter.flush();
				fileWriter.close();
				
				
				System.out.println("Report genaration has been successfully");
					
				
			}catch (Exception ex) {
				
				System.out.println("genarateSupplierDetailsReportException:" + ex.getMessage());
				
				
			}
			
		}

		@Override
		public void genarateWholeSaleOrderDetailsReport() {
				
			try {
				
				String query = "SELECT id, noOfBooks , bookName , supplierCompany  FROM wholesaleorder WHERE isActive = 1 ";
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				
				File directory = new File("F:\\SLIIT\\PROJECTS\\SA\\REPORT-GENERATE\\WholeSaleOrder");
				
				directory.mkdirs();
				
				File file = new File(directory,"wholesaleOrderList.txt");
				FileWriter fileWriter = new FileWriter(file);
				
				fileWriter.write(String.format("=================================================== WholeSaleOrder Details Report ============================================================================================\n\n"));
				fileWriter.write(
						
						String.format
						(
								"%25s %25s %25s %25s\n", 
								"WholesaleOrderId", "No Of Books", "Book Name", "Supplier Company"
						)
				);
				
				fileWriter.write(String.format("===================================================================================================================================================================================\n\n"));
				
				while(resultSet.next()) {
					
					fileWriter.write(
							
							String.format(
									
									"%20d %25s %25s %25s\n", 
									resultSet.getInt("id"),
									resultSet.getString("noOfBooks"),
									resultSet.getString("bookName"),
									resultSet.getString("supplierCompany")
									
							)
					);
					
					fileWriter.write(String.format("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"));
				}
				
				fileWriter.flush();
				fileWriter.close();
				
				
				System.out.println("WholeSaleOrder Report genaration has been successfully");
					
				
			}catch (Exception ex) {
				
				System.out.println("genarateWholeSaleDetailsReportException:" + ex.getMessage());
				
				
			}
			
		}
			
		
			
		

	}
