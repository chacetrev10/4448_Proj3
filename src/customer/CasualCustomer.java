package customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import rolls.Roll;

public class CasualCustomer extends Customer {

	private int numRollsPurchased;
	private String type;

	@Override
	public String[] orderComposition() {
		numRollsPurchased = (int) (Math.random() * 3) + 1;
		String[] order = new String[numRollsPurchased];
		type = chooseRoll();
		for (int x = 0; x < numRollsPurchased; x++) {
			order[x] = type;
		}
		return order;
	}

	@Override
	public String[] retryOrder(Map<String, List<Roll>> available) {
		String[] newOrder;
		List<String> rollTypesToList = new ArrayList<String>(Arrays.asList(rollTypes));
		int checked = 0;
		while (rollTypesToList.size() > 0) {
			if (available.get(type).size() > 0) {
				if (available.get(type).size() < numRollsPurchased) {
					newOrder = new String[available.get(type).size()];
				} else {
					newOrder = new String[numRollsPurchased];
				}
				for (int x = 0; x < newOrder.length; x++) {
					newOrder[x] = type;
				}
				return newOrder;
			}
			rollTypesToList.remove(type);
			type = rollTypesToList.get(0);
		}
		return null;
	}

}
