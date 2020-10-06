package store;

import rolls.EggRoll;
import rolls.JellyRoll;
import rolls.PastryRoll;
import rolls.Roll;
import rolls.SausageRoll;
import rolls.SpringRoll;

public class RollFactory {

	public Roll createRoll(String type) {
		Roll roll = null;
		switch(type) {
			case "egg": 
				roll = new EggRoll();
				break;
			case "jelly":
				roll = new JellyRoll();
				break;
			
			case "pastry":
				roll = new PastryRoll();
				break;
			case "sausage":
				roll = new SausageRoll();
				break;
			case "spring":
				roll = new SpringRoll();
				break;
				
		}
		return roll;
			
		
	}
		
}
