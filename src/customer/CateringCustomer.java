package customer;

import java.util.ArrayList;
import java.util.List;

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

}
