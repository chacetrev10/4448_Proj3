package rolls;

public class ExtraFilling extends ExtraDecorator {
	
	Roll roll;
	
	public ExtraFilling(Roll roll) {
		this.roll =roll;
	}
	@Override
	public String getDescription() {
		
		return roll.getDescription() + " with extra filling";
	}

	@Override
	public double getCost() {
		cost = roll.getCost()+ 2.00;
		return cost;
		
	}
	
}
