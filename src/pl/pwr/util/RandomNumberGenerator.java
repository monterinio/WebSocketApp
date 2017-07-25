package pl.pwr.util;

import java.util.Random;

public final class RandomNumberGenerator {
	private RandomNumberGenerator() {};
	
	public static int generateNumber(int range) {
		Random random = new Random();
		return random.nextInt(range)+1;
	}
}
