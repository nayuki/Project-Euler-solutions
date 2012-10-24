/* 
 * Solution to Project Euler problem 182
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p182 {
	
	private static int P = 1009;
	private static int Q = 3643;
	private static int TOTIENT = (P - 1) * (Q - 1);
	private static int N = P * Q;
	
	
	public static void main(String[] args) {
		int[] numUnconcealed = new int[TOTIENT];
		for (int e = 0; e < numUnconcealed.length; e++) {
			if (Library.gcd(e, TOTIENT) == 1)
				numUnconcealed[e] = countUnconcealed(e);
			else
				numUnconcealed[e] = Integer.MAX_VALUE;
		}
		
		int minUnconcealed = Integer.MAX_VALUE;
		for (int x : numUnconcealed)
			minUnconcealed = Math.min(x, minUnconcealed);
		
		long sum = 0;
		for (int i = 0; i < numUnconcealed.length; i++) {
			if (numUnconcealed[i] == minUnconcealed)
				sum += i;
		}
		System.out.println(sum);
	}
	
	
	private static int countUnconcealed(int e) {
		int count0 = 0;
		for (int m = 0; m < P; m++) {
			if (powMod(m, e, P) == m)
				count0++;
		}
		int count1 = 0;
		for (int m = 0; m < Q; m++) {
			if (powMod(m, e, Q) == m)
				count1++;
		}
		return count0 * count1;
	}
	
	
	private static int countUnconcealedSlow(int e) {
		int count = 0;
		for (int m = 0; m < N; m++) {
			if (powMod(m, e, N) == m)
				count++;
		}
		return count;
	}
	
	
	private static int powMod(int x, int y, int m) {
		if (y < 0)
			throw new IllegalArgumentException();
		int z = 1;
		for (; y != 0; y >>>= 1, x = (int)((long)x * x % m)) {
			if ((y & 1) != 0)
				z = (int)((long)z * x % m);
		}
		return z;
	}
	
}
