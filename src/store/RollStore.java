package store;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rolls.Roll;

public class RollStore {
	
	private Map <String, List<Roll>> rollInventory = new HashMap<>();
	private RollFactory factory;
	
	public RollStore(RollFactory factory) {
		this.factory = factory;
	}
	
	public Roll makeRolls(String type) {
		Roll roll = factory.createRoll(type);
		return roll;
	}
	
	
}
