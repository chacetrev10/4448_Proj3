package customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rolls.Roll;

public class CateringCustomer extends Customer {

	@Override
	public String[] orderComposition() {
		String[] order = new String[15];
		List<String> picked = new ArrayList<>();
		String type;
		for (int x = 0; x < 3; x++) {
			do {
				type = chooseRoll();
			} while (picked.contains(type));
			picked.add(type);
			
			for (int y = 0; y < 5; y++) {
				order[y + (x * 5)] = type;
			}
		}
		return order;
	}

	@Override
	public String[] retryOrder(Map<String, List<Roll>> available) {
		String[] newOrder = new String[15];
		int added = 0;
		for(String type: available.keySet()) {
			for(int x = 0; x < available.get(type).size(); x++) {
				if(added < 15) {
					newOrder[added] = type;
					added++;
				}else {
					break;
				}
			}
		}
		if(added == 15) {
			return newOrder;
		}else {
			return null;
		}
		
		
	}

}
