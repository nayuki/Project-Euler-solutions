/* 
 * Solution to Project Euler problem 62
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public final class p062 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p062().run());
	}
	
	
	public String run() {
		int numDigits = 0;
		Map<String,Integer> lowest = new HashMap<>();
		Map<String,Integer> counts = new HashMap<>();
		for (int i = 0; ; i++) {
			String numClass = getCubeNumberClass(i);
			
			if (numClass.length() > numDigits) {
				// Process and flush data for smaller number of digits
				int min = Integer.MAX_VALUE;
				for (String nc : counts.keySet()) {
					if (counts.get(nc) == 5)
						min = Math.min(lowest.get(nc), min);
				}
				if (min != Integer.MAX_VALUE)
					return cube(min).toString();
				
				lowest.clear();
				counts.clear();
				numDigits = numClass.length();
			}
			
			if (!lowest.containsKey(numClass)) {
				lowest.put(numClass, i);
				counts.put(numClass, 0);
			}
			counts.put(numClass, counts.get(numClass) + 1);
		}
	}
	
	
	private static String getCubeNumberClass(int x) {
		char[] digits = cube(x).toString().toCharArray();
		Arrays.sort(digits);
		return new String(digits);
	}
	
	
	private static BigInteger cube(int x) {
		return BigInteger.valueOf(x).pow(3);
	}
	
}
