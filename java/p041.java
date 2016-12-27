/* 
 * Solution to Project Euler problem 41
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p041 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p041().run());
	}
	
	
	public String run() {
		for (int n = 9; n >= 1; n--) {
			int[] digits = new int[n];
			for (int i = 0; i < digits.length; i++)
				digits[i] = i + 1;
			
			int result = -1;
			do {
				if (Library.isPrime(toInteger(digits)))
					result = toInteger(digits);
			} while (Library.nextPermutation(digits));
			if (result != -1)
				return Integer.toString(result);
		}
		throw new RuntimeException("Not found");
	}
	
	
	private static int toInteger(int[] digits) {
		int result = 0;
		for (int x : digits)
			result = result * 10 + x;
		return result;
	}
	
}
