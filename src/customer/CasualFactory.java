package customer;

public class CasualFactory extends CustomerFactory{
	@Override
	public Customer createCustomer(String type) {
		return new CasualCustomer();
	}
}
