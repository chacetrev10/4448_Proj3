package rolls;

public class ExtraTopping extends ExtraDecorator{
	Roll roll;
	
	public ExtraTopping(Roll roll) {
		this.roll =roll;
	}
	@Override
	public String getDescription() {
		return roll.getDescription() + " with extra toppings";
	}

	@Override
	public double getCost() {
		cost = roll.getCost()+ 1.25;
		return cost;
		
	}
	
}
