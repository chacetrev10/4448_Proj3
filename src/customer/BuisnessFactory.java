package customer;

public class BuisnessFactory extends CustomerFactory{
	@Override
	public Customer createCustomer(String type) {
		return new BuisnessCustomer();
	}
}
