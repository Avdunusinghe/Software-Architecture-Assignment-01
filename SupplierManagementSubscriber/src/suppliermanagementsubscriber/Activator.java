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
		
		renderSupplierDashboard(supplierService);
	}
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good Bye");
		bundleContext.ungetService(serviceReference);
	}

	private void renderSupplierDashboard(ISupplierService supplierService) {
		Scanner sc = new Scanner(System.in);
		
		int supplierChoice;
		String choice = "y";
		
		System.out.println("\n\n");
		System.out.println("==============Supplier Dashboard===============");
		System.out.println("\n");
		System.out.println("1 => Supplier Registration");
		System.out.println("2 => Get All Supplier Details");
		System.out.println("3 => Delete Supplier");
		System.out.println("4 => Get Supplier by Id");
		System.out.println("Please Select Your Option");
		
		supplierChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch(supplierChoice) {
			
			case 1:
				supplierService.saveSupplier();
				
				while(choice.equals("y")) {
					
					System.out.printf("\nDo you want to add another customer(y/n) ");
					choice = sc.nextLine().trim().toLowerCase();
					
					if(choice.equals("y")) {
						
						supplierService.saveSupplier();
					}
					
				}
				renderSupplierDashboard(supplierService);
				break;
				
			case 2:
				
				supplierService.getAllSupplierDetails();
				renderSupplierDashboard(supplierService);
				break;
				
			case 3:
				
				supplierService.deleteSupplier();
				
				while(choice.equals("y")) {
					
					System.out.printf("\nDo you want to delete another supplier(y/n) ");
					choice = sc.nextLine().trim().toLowerCase();
					
					if(choice.equals("y")) {
						
						supplierService.deleteSupplier();
					}
				}
				
				renderSupplierDashboard(supplierService);
				break;
			
			case 4:
				
				supplierService.getSupplierById();
				renderSupplierDashboard(supplierService);
				
			default:
				
				System.out.println("Supplier Option has been incorrect please try again ");
				renderSupplierDashboard(supplierService);

		
		}

	

	}
}
