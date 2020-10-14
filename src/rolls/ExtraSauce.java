package rolls;

public class ExtraSauce extends ExtraDecorator {


	
	public ExtraSauce(Roll roll) {
		this.roll =roll;
	}
	@Override
	public String getDescription() {
		
		return roll.getDescription() + " with extra sauce";
	}

	//Change price of roll for more sauce
	@Override
	public double getCost() {
		cost = roll.getCost();
		if(roll.getType().equals("egg")) {
			cost+=0.60;
		}else if(roll.getType().equals("jelly")) {
			cost+=0.30;
		}else if(roll.getType().equals("pastry")) {
			cost+=1.10;
		}else if(roll.getType().equals("sausage")) {
			cost+=0.95;
		}else {
			cost+=0.40;
		}
		return cost;
		
	}
	
}
