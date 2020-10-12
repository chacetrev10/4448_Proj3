package rolls;

public abstract class ExtraDecorator extends Roll{
	protected Roll roll;
	
	@Override
	public abstract String getDescription();
	
	public String getType() {
		return roll.getType();
	}
	
}
