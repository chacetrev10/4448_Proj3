package customer;

public class CasualCustomer extends Customer{

	@Override
	public String[] orderComposition() {
		int numRollsPurchased = (int) (Math.random()*3)+1;
		String[] order = new String [numRollsPurchased];
		for(int x = 0; x < numRollsPurchased; x++) {
			order[x] = chooseRoll();
		}
		return order;
	}

}
