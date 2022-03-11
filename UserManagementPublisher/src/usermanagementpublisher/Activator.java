package usermanagementpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	
	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("User Publisher Service Started");
		
		IUserService userService = new UserImpl();
		
		serviceRegistration = bundleContext.registerService(IUserService.class.getName(), userService, null);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Employee Publisher Service Stopped");
		serviceRegistration.unregister();
	}

}
