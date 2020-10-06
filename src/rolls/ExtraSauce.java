package rolls;

public class ExtraSauce extends ExtraDecorator {

	Roll roll;
	
	public ExtraSauce(Roll roll) {
		this.roll =roll;
	}
	@Override
	public String getDescription() {
		
		return roll.getDescription() + " with extra sauce";
	}

	@Override
	public double getCost() {
		cost = roll.getCost()+ 0.55;
		return cost;
		
	}
	
}
