package employeemanagementpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Employee Publisher Service Started");
		
		IEmployeeService employeeService = new EmployeeImpl();
		
		serviceRegistration = bundleContext.registerService(IEmployeeService.class.getName(), employeeService, null);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Employee Publisher Service Stopped");
		
		serviceRegistration.unregister();
		
	}

}
