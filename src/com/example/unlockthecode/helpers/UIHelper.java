package com.example.unlockthecode.helpers;

import java.util.Random;

public class UIHelper {

	/**
	 * Generates a random number that has the value less than maxValue
	 * @param maxValue the end value interval - this value is never returned by the method
	 * @return a random generated positive number <br/>
	 * if maxValue == Integer.MIN_VALUE =>  there is no upper limit
	 * else => the limit is represented by maxValue
	 */
	public static int generateRandomInt(int maxValue) {
		Random randomGenerator = new Random();
		return maxValue == Integer.MIN_VALUE ? 
				randomGenerator.nextInt() :
				randomGenerator.nextInt(maxValue);
	}
	
	public static int generateRandomInt() {
		return generateRandomInt(Integer.MIN_VALUE);
	}
	
}
