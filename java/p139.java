/* 
 * Solution to Project Euler problem 139
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p139 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p139().run());
	}
	
	
	
	private static final int LIMIT = 100000000;
	
	public String run() {
		/* 
		 * Pythagorean triples theorem:
		 *   Every primitive Pythagorean triple with a odd and b even can be expressed as
		 *   a = st, b = (s^2-t^2)/2, c = (s^2+t^2)/2, where s > t > 0 are coprime odd integers.
		 */
		int count = 0;
		for (int s = 3; s * s / 2 < LIMIT; s += 2) {
			for (int t = 1; t < s; t += 2) {
				int a = s * t;
				int b = (s * s - t * t) / 2;
				int c = (s * s + t * t) / 2;
				int p = a + b + c;
				if (p >= LIMIT)
					break;
				if (c % (a - b) == 0 && Library.gcd(s, t) == 1)
					count += (LIMIT - 1) / p;
			}
		}
		return Integer.toString(count);
	}
	
}
