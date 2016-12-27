/* 
 * Solution to Project Euler problem 92
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p092 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p092().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 7);
	
	public String run() {
		int count = 0;
		for (int i = 1; i < LIMIT; i++) {
			if (isClass89(i))
				count++;
		}
		return Integer.toString(count);
	}
	
	
	private static boolean isClass89(int x) {
		while (true) {
			switch (x) {
				case  1:  return false;
				case 89:  return true;
				default:  x = nextNumber(x);
			}
		}
	}
	
	
	private static int nextNumber(int x) {
		int sum = 0;
		while (x != 0) {
			sum += (x % 10) * (x % 10);
			x /= 10;
		}
		return sum;
	}
	
}
