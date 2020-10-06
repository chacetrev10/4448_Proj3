package rolls;

public abstract class Roll {
	String description;
	double cost;
	
	public String getDescription() {
		return description;
	}
	
	public abstract double getCost();
	
}
