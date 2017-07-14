/* 
 * Solution to Project Euler problem 231
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p231 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p231().run());
	}
	
	
	private static final int N = 20000000;
	private static final int K = 15000000;
	
	public String run() {
		smallestPrimeFactor = Library.listSmallestPrimeFactors(N);
		return Long.toString(factorialPrimeFactorSum(N)
			- factorialPrimeFactorSum(K)
			- factorialPrimeFactorSum(N - K));
	}
	
	
	private int[] smallestPrimeFactor;
	
	private long factorialPrimeFactorSum(int n) {
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			int j = i;
			while (j > 1) {
				int p = smallestPrimeFactor[j];
				sum += p;
				j /= p;
			}
		}
		return sum;
	}
	
}
