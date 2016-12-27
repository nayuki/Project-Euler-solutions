/* 
 * Solution to Project Euler problem 130
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p130 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p130().run());
	}
	
	
	public String run() {
		int sum = 0;
		int found = 0;
		for (int i = 7; found < 25; i += 2) {
			if (i % 5 != 0 && !Library.isPrime(i) && (i - 1) % findLeastDivisibleRepunit(i) == 0) {
				sum += i;
				found++;
			}
		}
		return Integer.toString(sum);
	}
	
	
	// Returns the smallest k such that R(k) is divisible by n.
	private static int findLeastDivisibleRepunit(int n) {
		if (n % 2 == 0 || n % 5 == 0)
			return 0;
		if (n > Integer.MAX_VALUE / 10)
			throw new IllegalArgumentException("Arithmetic overflow");
		
		int sum = 1;  // Equal to R(k) mod n
		int pow = 1;  // Equal to 10^k mod n
		int k = 1;
		while (sum % n != 0) {
			k++;
			pow = pow * 10 % n;
			sum = (sum + pow) % n;
		}
		return k;
	}
	
}
