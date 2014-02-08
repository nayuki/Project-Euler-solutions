/* 
 * Solution to Project Euler problem 127
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p127 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p127().run());
	}
	
	
	private static final int LIMIT = 120000;
	
	public String run() {
		int[] smallestPrimeFactor = Library.listSmallestPrimeFactors(LIMIT - 1);
		
		int[] rads = new int[LIMIT];
		for (int i = 1; i < rads.length; i++) {
			int n = i;
			int rad = 1;
			while (n > 1) {
				int p = smallestPrimeFactor[n];
				do n /= p;
				while (n % p == 0);
				rad *= p;
			}
			rads[i] = rad;
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
