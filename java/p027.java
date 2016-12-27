/* 
 * Solution to Project Euler problem 27
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p027 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p027().run());
	}
	
	
	public String run() {
		int bestNum = 0;
		int bestA = 0;
		int bestB = 0;
		for (int a = -1000; a <= 1000; a++) {
			for (int b = -1000; b <= 1000; b++) {
				int num = numberOfConsecutivePrimesGenerated(a, b);
				if (num > bestNum) {
					bestNum = num;
					bestA = a;
					bestB = b;
				}
			}
		}
		return Integer.toString(bestA * bestB);
	}
	
	
	private static int numberOfConsecutivePrimesGenerated(int a, int b) {
		for (int i = 0; ; i++) {
			int n = i * i + i * a + b;
			if (n < 0 || !Library.isPrime(n))
				return i;
		}
	}
	
}
