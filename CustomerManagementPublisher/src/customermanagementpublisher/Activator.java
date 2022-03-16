package customermanagementpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("User Publisher Service Started");
		
		ICustomerService customerService = new CustomerImpl();
		
		serviceRegistration = bundleContext.registerService(ICustomerService.class.getName(), customerService , null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Customer Publisher Service Stopped");
		
		serviceRegistration.unregister();
	}

}
