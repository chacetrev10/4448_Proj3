package customer;

import java.util.List;
import java.util.Map;

import rolls.Roll;

public class BuisnessCustomer extends Customer{
	@Override
	public String[] orderComposition() {
		String[] order = {"egg", "pastry", "sausage", "spring", "jelly","egg", "pastry", "sausage", "spring", "jelly"};
		return order;
	}

	@Override
	public String[] retryOrder(Map<String, List<Roll>> available) {
		return null;
	}
}
