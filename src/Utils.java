import java.util.Random;


public class Utils {	
	
	public static int generateRandomInt(int lower, int upper){
		return new Random().nextInt((upper - lower) + 1) + lower;
	}

}
