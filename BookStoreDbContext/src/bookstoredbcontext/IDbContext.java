package bookstoredbcontext;

import java.sql.Connection;

public interface IDbContext {
	
	public Connection getDatabaseConnection();

}
