package inventorymanagementsubscriber;

import java.util.Scanner;

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
		
		renderInventoryDashBoard(inventoryService);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Good Bye");
		
		bundleContext.ungetService(serviceReference);
	}
	
	public void renderInventoryDashBoard(IInventoryService inventoryService) {
		
		Scanner sc = new Scanner(System.in);
		int userChoice;
		
		System.out.println("\n\n");
		System.out.println("==============Inventory Dashboard===============");
		System.out.println("1 => Save Book");
		System.out.println("2 => Get All Book Details");
		System.out.println("3 => Delete Book");
		System.out.println("4 => Get Book By Id");
		
		System.out.println("Please Select Your Option");
		
		userChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch(userChoice) {
		
		case 1:
			inventoryService.saveBooks();;
			renderInventoryDashBoard(inventoryService);
			break;
			
		case 2:
			inventoryService.getAllBooksDetails();;
			renderInventoryDashBoard(inventoryService);
			break;
			
		case 3:
			inventoryService.deleteBook();
			renderInventoryDashBoard(inventoryService);
			break;
		
		case 4:
			inventoryService.getBookById();
			renderInventoryDashBoard(inventoryService);
			break;
			
		default:
			
			System.out.println("User Option has been incorrect please try again ");
			renderInventoryDashBoard(inventoryService);

	  }

	}
	
}
