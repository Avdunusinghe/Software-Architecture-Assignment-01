package employeemanagementsubscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import employeemanagementpublisher.IEmployeeService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Start Employee Subscriber Service");
		
		serviceReference = bundleContext.getServiceReference(IEmployeeService.class.getName());
		
		IEmployeeService employeeService = (IEmployeeService)bundleContext.getService(serviceReference);
		
		renderEmployeeDashborad(employeeService);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Good Bye");
		
		bundleContext.ungetService(serviceReference);
		
	}
	
	public void renderEmployeeDashborad(IEmployeeService employeeService ) {
		
		Scanner sc = new Scanner(System.in);
		int userChoice;
		
		System.out.println("\n\n");
		System.out.println("==============Employee Management Dashboard===============");
		System.out.println("1 => Employee Registration");
		System.out.println("Please Select Your Option");
		
		userChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch(userChoice) {
			
			case 1:
				employeeService.saveEmployee();
				renderEmployeeDashborad(employeeService);
				break;
				
			default:
				
				System.out.println("User Option has been incorrect please try again ");
				renderEmployeeDashborad(employeeService);

		}
		
	}

}
