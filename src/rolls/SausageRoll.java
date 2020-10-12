package rolls;

public class SausageRoll extends Roll{
	
	public SausageRoll() {
		description = "Sausage Roll";
		type = "sausage";
	}
	
	public double getCost() {
		cost = 4.00;
		return cost;
	}
}
