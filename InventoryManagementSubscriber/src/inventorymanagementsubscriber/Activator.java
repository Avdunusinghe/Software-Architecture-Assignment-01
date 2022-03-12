package inventorymanagementsubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import inventorymanagementpublisher.IInventoryService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;


	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Start Inventory Subscriber Service");
		
		serviceReference = bundleContext.getServiceReference(IInventoryService.class.getName());
		
		IInventoryService inventoryService = (IInventoryService)bundleContext.getService(serviceReference);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
	}

}
