/* 
 * Solution to Project Euler problem 47
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p047 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p047().run());
	}
	
	
	public String run() {
		for (int i = 2; ; i++) {
			if (       has4PrimeFactors(i + 0)
			        && has4PrimeFactors(i + 1)
			        && has4PrimeFactors(i + 2)
			        && has4PrimeFactors(i + 3))
				return Integer.toString(i);
		}
	}
	
	
	private static boolean has4PrimeFactors(int n) {
		return countDistinctPrimeFactors(n) == 4;
	}
	
	
	private static int countDistinctPrimeFactors(int n) {
		int count = 0;
		for (int i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0) {
				do n /= i;
				while (n % i == 0);
				count++;
				end = Library.sqrt(n);
			}
		}
		if (n > 1)
			count++;
		return count;
	}
	
}
