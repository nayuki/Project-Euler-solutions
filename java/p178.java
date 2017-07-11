/* 
 * Solution to Project Euler problem 178
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p178 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p178().run());
	}
	
	
	private static final int LIMIT = 40;
	
	public String run() {
		// Dynamic programming: ways[digits][head][low][high] is the number of pandigital
		// step numbers such that each number is 'digits' long, starts with the digit 'head'
		// (can be 0), and the union of all the digits equals the interval [low, high]
		BigInteger[][][][] ways = new BigInteger[LIMIT + 1][10][10][10];
		BigInteger sum = BigInteger.ZERO;
		for (int digits = 0; digits < ways.length; digits++) {
			for (int head = 0; head < 10; head++) {
				for (int low = 0; low < 10; low++) {
					for (int high = 0; high < 10; high++) {
						
						BigInteger val;
						if (digits <= 1)
							val = (low == head && head == high) ? BigInteger.ONE : BigInteger.ZERO;
						else {
							val = BigInteger.ZERO;
							BigInteger[][][] prevWays = ways[digits - 1];
							if (head - 1 >= low) {
								val = val.add(prevWays[head - 1][low][high]);
								if (head == high)
									val = val.add(prevWays[head - 1][low][high - 1]);
							}
							if (head + 1 <= high) {
								val = val.add(prevWays[head + 1][low][high]);
								if (head == low)
									val = val.add(prevWays[head + 1][low + 1][high]);
							}
						}
						ways[digits][head][low][high] = val;
						if (head > 0 && low == 0 && high == 9)
							sum = sum.add(val);
					}
				}
			}
		}
		return sum.toString();
	}
	
}
