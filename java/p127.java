/* 
 * Solution to Project Euler problem 127
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p127 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p127().run());
	}
	
	
	private static final int LIMIT = 120000;
	
	public String run() {
		// Modification of the Sieve of Eratosthenes
		int[] rads = new int[LIMIT];
		Arrays.fill(rads, 1, rads.length, 1);
		for (int i = 2; i < rads.length; i++) {
			if (rads[i] == 1) {
				for (int j = i; j < rads.length; j += i)
					rads[j] *= i;
			}
		}
		
		/* 
		 * Notes:
		 * - gcd(a,b) = gcd(a,c) = gcd(b,c), so we only need to compute one of them.
		 * - Since {a, b, c} are mutually coprime, rad(a * b * c) = rad(a) * rad(b) * rad(c).
		 * - rad(a)*rad(b)*rad(c) < c implies rad(a)*rad(b)*rad(c) <= c-1 implies rad(a)*rad(b) <= floor((c-1)/rad(c)).
		 */
		long sum = 0;
		for (int c = 2; c < LIMIT; c++) {
			int thres = (c - 1) / rads[c];
			for (int a = 1; ; a++) {
				int b = c - a;
				if (b <= a)
					break;
				
				// The first two conditions are just optional optimizations
				if (rads[a] <= thres && rads[b] <= thres && (long)rads[a] * rads[b] <= thres && Library.gcd(a, b) == 1)
					sum += c;
			}
		}
		return Long.toString(sum);
	}
	
}
