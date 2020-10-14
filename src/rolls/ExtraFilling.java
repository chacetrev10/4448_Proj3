package rolls;

public class ExtraFilling extends ExtraDecorator {
		
	
	public ExtraFilling(Roll roll) {
		this.roll =roll;
	}
	@Override
	public String getDescription() {
		
		return roll.getDescription() + " with extra filling";
	}

	//Change price of roll for more filling
	@Override
	public double getCost() {
		cost = roll.getCost();
		if(roll.getType().equals("egg")) {
			cost+=0.40;
		}else if(roll.getType().equals("jelly")) {
			cost+=0.20;
		}else if(roll.getType().equals("pastry")) {
			cost+=1.20;
		}else if(roll.getType().equals("sausage")) {
			cost+=0.75;
		}else {
			cost+=0.10;
		}
		
			
		return cost;
		
	}
	
}
