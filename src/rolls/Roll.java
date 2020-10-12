package rolls;

public abstract class Roll {
	protected String description;
	protected double cost;
	protected String type;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public String getDescription() {
		return description;
	}
	
	public abstract double getCost();
	
}
