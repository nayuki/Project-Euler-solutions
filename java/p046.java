/* 
 * Solution to Project Euler problem 46
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p046 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p046().run());
	}
	
	
	public String run() {
		for (int i = 9; ; i += 2) {
			if (!satisfiesConjecture(i))
				return Integer.toString(i);
		}
	}
	
	
	private static boolean satisfiesConjecture(int n) {
		if (n % 2 == 0 || Library.isPrime(n))
			return true;
		
		// Now n is an odd composite number
		for (int i = 1; i * i * 2 <= n; i++) {
			if (Library.isPrime(n - i * i * 2))
				return true;
		}
		return false;
	}
	
}
