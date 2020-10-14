package rolls;

public abstract class ExtraDecorator extends Roll{
	protected Roll roll;
	
	@Override
	public abstract String getDescription();
	
	//Decorate roll with extra filling, toppings, or sauce
	public String getType() {
		return roll.getType();
	}
	
}
