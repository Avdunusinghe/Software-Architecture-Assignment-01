package bookstoredbcontext;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class DbContextImpl implements IDbContext {
	
	private Connection dbContextConnection;
	private final String dbContextDriverName;
	private String dbContextConnectionString;
	private String dbContextUser;
	private String dbContextPassword;
	
	public DbContextImpl() {
		
		this.dbContextDriverName = "com.mysql.jdbc.Driver";
		this.dbContextConnectionString = "jdbc:mysql://localhost:3306/bookStoreDb?autoReconnect=true&useSSL=false";	
		this.dbContextUser = "root";
		this.dbContextPassword = "1qaz2wsx@";
	}


	@Override
	public Connection getDatabaseConnection() {
		
		try {
			
			Class.forName(dbContextDriverName);
			dbContextConnection = (Connection)DriverManager.getConnection(dbContextConnectionString, dbContextUser, dbContextPassword);
			
			System.out.println("Database Connection Eshtablished");
			
		}catch(Exception ex) {
			
			System.out.println("dbConnectionError: " + ex.getMessage());
		}
		
		return dbContextConnection;
	}

}
