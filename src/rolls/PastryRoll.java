package rolls;

public class PastryRoll extends Roll{
	
	public PastryRoll() {
		description = "Pastry Roll";
		type = "pastry";
	}
	
	public double getCost() {
		cost = 1.60;
		return cost;
	}
}
