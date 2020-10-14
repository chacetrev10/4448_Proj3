package customer;

import java.util.List;
import java.util.Map;

import rolls.Roll;

public class BuisnessCustomer extends Customer{
	
	public BuisnessCustomer() {
		customerType = "business";
	}
	
	//Create business customer order, since all are the same
	@Override
	public String[] orderComposition() {
		String[] order = {"egg", "pastry", "sausage", "spring", "jelly","egg", "pastry", "sausage", "spring", "jelly"};
		return order;
	}

	//If a roll is out of stock, the order will not happen
	@Override
	public String[] retryOrder(Map<String, List<Roll>> available) {
		return null;
	}
}
