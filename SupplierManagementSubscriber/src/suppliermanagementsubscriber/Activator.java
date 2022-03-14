package suppliermanagementsubscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import suppliermanagementpublisher.ISupplierService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Start Supplier Subscriber Service");
		
		serviceReference = bundleContext.getServiceReference(ISupplierService.class.getName());
		
		ISupplierService supplierService = (ISupplierService)bundleContext.getService(serviceReference);
		
		renderUserDashboard(supplierService);
	}
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good Bye");
		bundleContext.ungetService(serviceReference);
	}

	private void renderUserDashboard(ISupplierService supplierService) {
		Scanner sc = new Scanner(System.in);
		int supplierChoice;
		
		System.out.println("\n\n");
		System.out.println("==============Supplier Dashboard===============");
		System.out.println("1 => Supplier Registration");
		System.out.println("Please Select Your Option");
		
		supplierChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch(supplierChoice) {
			
			case 1:
				supplierService.saveSupplier();
				renderUserDashboard(supplierService);
				break;
				
			default:
				
				System.out.println("Supplier Option has been incorrect please try again ");
				renderUserDashboard(supplierService);

		
		}

	

	}
}
