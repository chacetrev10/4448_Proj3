package customer;

public class BuisnessCustomer extends Customer{
	@Override
	public String[] orderComposition() {
		String[] order = {"egg", "pastry", "sausage", "spring", "jelly","egg", "pastry", "sausage", "spring", "jelly"};
		return order;
	}
}
