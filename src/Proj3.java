import rolls.ExtraFilling;
import rolls.ExtraSauce;
import rolls.ExtraTopping;
import rolls.JellyRoll;
import rolls.Roll;
import rolls.SausageRoll;

public class Proj3 {
	public static void main(String[]args) {
		
		Roll r1 = new JellyRoll();
		r1 = new ExtraSauce(r1);
		r1 = new ExtraFilling(r1);
		r1 = new ExtraTopping(r1);
		System.out.println(r1.getDescription());
		System.out.println(r1.getCost());
		
		
	}
}
