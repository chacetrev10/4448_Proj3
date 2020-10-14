package rolls;

public class ExtraTopping extends ExtraDecorator{
	
	
	public ExtraTopping(Roll roll) {
		this.roll =roll;
	}
	@Override
	public String getDescription() {
		return roll.getDescription() + " with extra toppings";
	}

	//Change price of roll for more toppings
	@Override
	public double getCost() {
		cost = roll.getCost();
		if(roll.getType().equals("egg")) {
			cost+=1.00;
		}else if(roll.getType().equals("jelly")) {
			cost+=0.90;
		}else if(roll.getType().equals("pastry")) {
			cost+=1.50;
		}else if(roll.getType().equals("sausage")) {
			cost+=1.75;
		}else {
			cost+=0.60;
		}
		return cost;
	
		
	}
	
}
