package suppliermanagementpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	
	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Supplier Publisher Service Started");
		
		ISupplierService supplierService = new SupplierImpl();
		
		serviceRegistration = bundleContext.registerService(ISupplierService.class.getName(), supplierService, null);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Supplier Publisher Service Stopped");
		serviceRegistration.unregister();
	}

}
