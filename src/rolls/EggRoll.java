package rolls;

public class EggRoll extends Roll{

	public EggRoll() {
		description = "Egg Roll";
		type = "egg";
	}
	
	public double getCost() {
		cost = 1.50;
		return cost;
	}
}
