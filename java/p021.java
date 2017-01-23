/* 
 * Solution to Project Euler problem 21
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p021 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p021().run());
	}
	
	
	/* 
	 * We find the sum of proper divisors of a number by brute force,
	 * and apply the definition of an amicable number straightforwardly.
	 */
	
	public String run() {
		int sum = 0;
		for (int i = 1; i < 10000; i++) {
			if (isAmicable(i))
				sum += i;
		}
		return Integer.toString(sum);
	}
	
	
	private static boolean isAmicable(int n) {
		int m = divisorSum(n);
		return m != n && divisorSum(m) == n;
	}
	
	
	private static int divisorSum(int n) {
		int sum = 0;
		for (int i = 1; i < n; i++) {
			if (n % i == 0)
				sum += i;
		}
		return sum;
	}
	
}
