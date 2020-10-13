package test;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

import rolls.EggRoll;
import rolls.JellyRoll;
import rolls.SausageRoll;
import rolls.PastryRoll;
import rolls.SpringRoll;
import rolls.Roll;
import rolls.ExtraFilling;
import rolls.ExtraSauce;
import rolls.ExtraTopping;
import customer.BuisnessCustomer;
import customer.CasualCustomer;
import customer.CateringCustomer;


public class MyUnitTest {
	
	//Test that the cost of an egg roll is 1.50
	@Test
	public void testEggRollCost() {
		EggRoll eggRoll = new EggRoll();
		double result = eggRoll.getCost();
		
		try {
			assertEquals(1.50, result, .0001);
		} catch (AssertionError e) {
		    System.out.println("EggRoll price incorrect, testEggRollCost failed");
		    throw e;
		}
		System.out.println("EggRoll price correct, testEggRollCost passed");
		
	}
	
	//Test that a business order contains 2 of each type of roll
	@Test
	public void testBusinessOrder() {
		BuisnessCustomer bCustomer = new BuisnessCustomer();
		String[] correctOrder = {"egg", "pastry", "sausage", "spring", "jelly","egg", "pastry", "sausage", "spring", "jelly"};
		String[] result = bCustomer.orderComposition();
		
		try {
			assertArrayEquals(correctOrder, result);
		} catch (AssertionError e) {
		    System.out.println("BusinessCustomer order composition incorrect, testBusinessOrder failed");
		    throw e;
		}
		System.out.println("BusinessCustomer order composition correct, testBusinessOrder passed");
		
	}
	
	//Test that a casual order contains between 1 and 3 rolls
	@Test
	public void testLenCasualOrder() {
		CasualCustomer cCustomer = new CasualCustomer();
		int result = cCustomer.orderComposition().length;
		
		try {
			assertTrue(result >= 1 && result <= 3);
		} catch (AssertionError e) {
		    System.out.println("Length of CasualCustomer order incorrect, testLenCasualOrder failed");
		    throw e;
		}
		System.out.println("Length of CasualCustomer order is between 1 and 3 rolls, testLenCasualOrder passed");
		
	}
	
	//Test that a catering order contains only 15 rolls
	@Test
	public void testLenCateringOrder() {
		CateringCustomer catCustomer = new CateringCustomer();
		int result = catCustomer.orderComposition().length;
		
		try {
			assertEquals(15, result);
		} catch (AssertionError e) {
		    System.out.println("Length of CateringCustomer order incorrect, testLenCateringOrder failed");
		    throw e;
		}
		System.out.println("Length of CateringCustomer order is 15 rolls, testLenCateringOrder passed");
		
	}
	
	//Test that a catering order contains only 3 different types of rolls
	//Used this source to figure out syntax for converting array to LinkedHashSet:
	//https://stackoverflow.com/questions/15752180/how-to-get-unique-items-from-an-array
	@Test
	public void testTypeCateringOrder() {
		CateringCustomer catCustomer = new CateringCustomer();
		String[] orderComp = catCustomer.orderComposition();
		Set<String> rollTypes = new LinkedHashSet<String>();
		rollTypes.addAll(Arrays.asList(orderComp));
		int numRollTypes = rollTypes.size();
		
		try {
			assertEquals(3, numRollTypes);
		} catch (AssertionError e) {
		    System.out.println("Number of different types of rolls in catering order incorrect, testTypeCateringOrder failed");
		    throw e;
		}
		System.out.println("Three different types of rolls found in catering order, testTypeCateringOrder passed");
		
	}
	
	//Test that the extra filling orders contain the appropriate description
	@Test
	public void testExtraFillingDescription() {
		Roll eggRoll = new EggRoll(); 
		ExtraFilling extraEgg = new ExtraFilling(eggRoll);
		String description = extraEgg.getDescription();
		
		try {
			assertTrue(description.contains("with extra filling"));
		} catch (AssertionError e) {
		    System.out.println("Description of ExtraFilling incorrect, testExtraFillingDescription failed");
		    throw e;
		}
		System.out.println("Description of ExtraFilling correct, testExtraFillingDescription passed");
		
	}
	
	//Test that the extra filling orders have the correct additional cost
	@Test
	public void testExtraFillingCost() {
		Roll jellyRoll = new JellyRoll();
		ExtraFilling extraJelly = new ExtraFilling(jellyRoll);
		double extraCost = extraJelly.getCost();
		
		try {
			assertEquals(1.10, extraCost, .001);
		} catch (AssertionError e) {
		    System.out.println("Cost of ExtraFilling order incorrect, testExtraFillingCost failed");
		    throw e;
		}
		System.out.println("Cost of ExtraFilling order correct, testExtraFillingCost passed");
		
	}
	
	//Test that the extra sauce orders have the correct additional cost
	@Test
	public void testExtraSauceCost() {
		Roll sausageRoll = new SausageRoll();
		ExtraSauce extraSausage = new ExtraSauce(sausageRoll);
		double extraCost = extraSausage.getCost();
		
		try {
			assertEquals(4.95, extraCost, .001);
		} catch (AssertionError e) {
		    System.out.println("Cost of ExtraSauce order incorrect, testExtraSauceCost failed");
		    throw e;
		}
		System.out.println("Cost of ExtraSauce order correct, testExtraSauceCost passed");
		
	}
	
	//Test that the extra topping orders have the correct additional cost
	@Test
	public void testExtraTopCost() {
		Roll pastryRoll = new PastryRoll();
		ExtraTopping extraPastry = new ExtraTopping(pastryRoll);
		double extraCost = extraPastry.getCost();
		
		try {
			assertEquals(3.10, extraCost, .001);
		} catch (AssertionError e) {
		    System.out.println("Cost of ExtraTopping order incorrect, testExtraTopCost failed");
		    throw e;
		}
		System.out.println("Cost of ExtraTopping order correct, testExtraTopCost passed");
		
	}
	
	//Test that the extra sauce orders contain the appropriate description
	@Test
	public void testExtraSauceDescription() {
		Roll springRoll = new SpringRoll();
		ExtraSauce extraSpring = new ExtraSauce(springRoll);
		String description = extraSpring.getDescription();
		
		try {
			assertTrue(description.contains("with extra sauce"));
		} catch (AssertionError e) {
		    System.out.println("Description of ExtraSauceincorrect, testExtraSauceDescription failed");
		    throw e;
		}
		System.out.println("Description of ExtraSauce correct, testExtraSauceDescription passed");
		
	}
}
