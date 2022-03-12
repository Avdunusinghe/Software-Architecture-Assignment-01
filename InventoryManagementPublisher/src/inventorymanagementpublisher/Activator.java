package inventorymanagementpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;


	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Inventory Publisher Service Started");
		
		IInventoryService inventoryService = new InventoryImpl();
		
		serviceRegistration = bundleContext.registerService(IInventoryService.class.getName(), inventoryService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
	}

}
