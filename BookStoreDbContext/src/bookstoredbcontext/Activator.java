package bookstoredbcontext;

import java.sql.Connection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;
	private Connection conn;
	
	IDbContext context = new DbContextImpl();

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("BookStore Data Publisher Service Started");
		IDbContext dbContext = new DbContextImpl();
		dbContext.getDatabaseConnection();
		serviceRegistration = bundleContext.registerService(IDbContext.class.getName(), dbContext, null);
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("BookStore Data Publisher Service Stop");
		serviceRegistration.unregister();
	}

}
