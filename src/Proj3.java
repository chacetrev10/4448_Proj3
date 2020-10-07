import customer.BuisnessCustomer;
import customer.CasualCustomer;
import customer.CateringCustomer;
import customer.Customer;
import rolls.ExtraFilling;
import rolls.ExtraSauce;
import rolls.ExtraTopping;
import rolls.JellyRoll;
import rolls.Roll;
import rolls.SausageRoll;
import store.RollFactory;
import store.RollStore;
import store.StoreObserver;

public class Proj3 {
	public static void main(String[]args) {
		RollFactory factory = new RollFactory();
		RollStore store = new RollStore(factory);
		StoreObserver observe = new StoreObserver();
		store.makeRolls("egg");
		store.makeRolls("jelly");
		store.makeRolls("sausage");
		store.makeRolls("spring");
		store.makeRolls("pastry");
		store.addPropertyChangeListener(observe);
		Roll r1 = store.sellRoll("egg");
		System.out.println(r1.getDescription());
		r1 = store.sellRoll("egg");
		System.out.println(r1.getDescription());
		r1 = store.sellRoll("egg");
		System.out.println(r1.getDescription());
		store.printInventory();
		Customer cus = new CateringCustomer();
		String [] order = cus.orderComposition();
		
		
	}
}
