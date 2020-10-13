package customer;

public class CateringFactory extends CustomerFactory{

	@Override
	public Customer createCustomer(String type) {
		return new CateringCustomer();
	}

}
