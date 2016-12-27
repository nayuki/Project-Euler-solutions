/* 
 * Solution to Project Euler problem 132
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p132 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p132().run());
	}
	
	
	public String run() {
		int sum = 0;
		int count = 0;
		for (int i = 2; count < 40; i++) {
			if (Library.isPrime(i) && repunitMod(1000000000, i) == 0) {
				sum += i;
				count++;
			}
		}
		return Integer.toString(sum);
	}
	
	
	private static int repunitMod(int k, int m) {
		return (Library.powMod(10, k, m * 9) - 1) / 9;
	}
	
}
