/* 
 * Solution to Project Euler problem 50
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p050 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p050().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 6);
	
	
	public String run() {
		boolean[] isPrime = Library.listPrimality(LIMIT);
		int[] primes = Library.listPrimes(LIMIT);
		
		long maxSum = 0;
		int maxRun = -1;
		for (int i = 0; i < primes.length; i++) {  // For each index of a starting prime number
			int sum = 0;
			for (int j = i; j < primes.length; j++) {  // For each end index (inclusive)
				sum += primes[j];
				if (sum > LIMIT)
					break;
				else if (j - i > maxRun && sum > maxSum && isPrime[sum]) {
					maxSum = sum;
					maxRun = j - i;
				}
			}
		}
		return Long.toString(maxSum);
	}
	
}
