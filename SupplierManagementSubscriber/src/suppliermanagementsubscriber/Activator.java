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
		System.out.println("5 =>  WholeSale Order of Books");
		System.out.println("6 => Get wholesale order list");
		System.out.println("7 => Delete Wholesale Order");
		System.out.println("8 => Get Wholesale Order by Id");
		System.out.println("9 => Get Supplier Report");
		System.out.println("10 => Get WholesaleOrder Report");
		
		System.out.println("Please Select Your Option");
		
		supplierChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch(supplierChoice) {
			
			case 1:
				supplierService.saveSupplier();
				
				while(choice.equals("y")) {
					
					System.out.printf("\nDo you want to add another supplier(y/n) ");
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
				
			case 5:
				supplierService.wholesaleOrder();
				
				while(choice.equals("y")) {
					
					System.out.printf("\nDo you want to add another wholesale Order(y/n) ");
					choice = sc.nextLine().trim().toLowerCase();
					
					if(choice.equals("y")) {
						
						supplierService.wholesaleOrder();
					}
					
				}
				renderSupplierDashboard(supplierService);
				break;
				
			case 6:
				
				supplierService.getAllWholeSaleOrders();
				renderSupplierDashboard(supplierService);
				break;
				
			case 7:
				
				supplierService.deleteWholesaleOrder();
				
				while(choice.equals("y")) {
					
					System.out.printf("\nDo you want to delete another wholesale order(y/n) ");
					choice = sc.nextLine().trim().toLowerCase();
					
					if(choice.equals("y")) {
						
						supplierService.deleteWholesaleOrder();
					}
				}
				
				renderSupplierDashboard(supplierService);
				break;
			
			case 8:
				
				supplierService.getWholesaleOrderById();
				renderSupplierDashboard(supplierService);
			
			case 9:
				
				supplierService.genarateSupplierDetailsReport();
				renderSupplierDashboard(supplierService);
				
			case 10:
				
				supplierService.genarateWholeSaleOrderDetailsReport();
				renderSupplierDashboard(supplierService);
				
				
				
			default:
				
				System.out.println("Supplier Option has been incorrect please try again ");
				renderSupplierDashboard(supplierService);

		
		}

	

	}
}
