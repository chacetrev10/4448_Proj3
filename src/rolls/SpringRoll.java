package rolls;

public class SpringRoll extends Roll {
	
	public SpringRoll() {
		description = "Spring Roll";
		type = "spring";
	}
	
	public double getCost() {
		cost = 2.50;
		return cost;
	}
}
