package usermanagementsubscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import usermanagementpublisher.IUserService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Start User Subscriber Service");
		
		serviceReference = bundleContext.getServiceReference(IUserService.class.getName());
		
		IUserService userService = (IUserService)bundleContext.getService(serviceReference);
		
		renderUserDashboard(userService);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good Bye");
		bundleContext.ungetService(serviceReference);
	}
	
	
	public void renderUserDashboard(IUserService userService) {
		
		Scanner sc = new Scanner(System.in);
		int userChoice;
		
		System.out.println("\n\n");
		System.out.println("==============User Dashboard===============");
		System.out.println("1 => User Registration");
		System.out.println("Please Select Your Option");
		
		userChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch(userChoice) {
			
			case 1:
				userService.saveUser();
				renderUserDashboard(userService);
				break;
				
			default:
				
				System.out.println("User Option has been incorrect please try again ");
				renderUserDashboard(userService);

		}
		
		
	}

}
