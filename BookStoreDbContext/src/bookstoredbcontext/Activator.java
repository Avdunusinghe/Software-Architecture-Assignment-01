package bookstoredbcontext;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;


	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("BookStore Data Publisher Service Started");
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("BookStore Data Publisher Service Stop");
	}

}
