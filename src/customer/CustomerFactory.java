package customer;

public class CustomerFactory {
	public Customer createCustomer(String type) {
		Customer cust = null;
		switch(type) {
			case "casual": 
				cust = new CasualCustomer();
				break;
			case "buisness":
				cust = new BuisnessCustomer();
				break;
			
			case "catering":
				cust = new CateringCustomer();
				break;
			
				
		}
		return cust;
			
		
	}
}
