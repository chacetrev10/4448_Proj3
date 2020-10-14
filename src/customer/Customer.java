package customer;

import java.util.List;
import java.util.Map;

import rolls.Roll;

public abstract class Customer {
	
	public String customerType;
	
	protected String[] rollTypes = {"egg", "pastry", "sausage", "spring", "jelly"};
	
	public abstract String[] orderComposition();
	
	//Make random roll selection
	protected String chooseRoll() {
		int randomChoice = (int) (Math.random()*5);
		return rollTypes[randomChoice];
	}
	
	public abstract String[] retryOrder( Map<String, List<Roll>> available);
	
	
			
}
