/* 
 * Solution to Project Euler problem 329
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p329 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p329().run());
	}
	
	
	private static final int START_NUM = 1;
	private static final int END_NUM = 500;
	private static final String CROAK_SEQ = "PPPPNNPPPNPPNPN";
	
	private static final int NUM_JUMPS = CROAK_SEQ.length() - 1;
	private static final int NUM_TRIALS = 1 << NUM_JUMPS;
	
	
	public String run() {
		long globalNumerator = 0;
		boolean[] isPrime = Library.listPrimality(END_NUM);
		for (int i = START_NUM; i <= END_NUM; i++) {
			for (int j = 0; j < NUM_TRIALS; j++) {
				int pos = i;
				int trialNumerator = 1;
				if (isPrime[pos] == (CROAK_SEQ.charAt(0) == 'P'))
					trialNumerator *= 2;
				for (int k = 0; k < NUM_JUMPS; k++) {
					if (pos <= START_NUM)
						pos++;
					else if (pos >= END_NUM)
						pos--;
					else if (((j >>> k) & 1) == 0)
						pos++;
					else
						pos--;
					if (isPrime[pos] == (CROAK_SEQ.charAt(k + 1) == 'P'))
						trialNumerator *= 2;
				}
				globalNumerator += trialNumerator;
			}
		}
		
		BigInteger globalDenominator = BigInteger.valueOf(END_NUM + 1L - START_NUM)
			.shiftLeft(NUM_JUMPS).multiply(BigInteger.valueOf(3).pow(CROAK_SEQ.length()));
		return new Fraction(BigInteger.valueOf(globalNumerator), globalDenominator).toString();
	}
	
}
