package customer;

public abstract class Customer {
	
	private String[] rollTypes = {"egg", "pastry", "sausage", "spring", "jelly"};
	
	public abstract String[] orderComposition();
	
	protected String chooseRoll() {
		int randomChoice = (int) (Math.random()*5);
		return rollTypes[randomChoice];
	}
	
			
}
