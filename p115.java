/* 
 * Solution to Project Euler problem 115
 * By Nayuki Minase
 */


public class p115 {
	
	public static void main(String[] args) {
		for (int i = 1; ; i++) {
			if (ways(50, i) > 1000000) {
				System.out.println(i);
				break;
			}
		}
	}
	
	
	private static long ways(int m, int n) {
		long[] ways = new long[n + 1];  // Memoization
		ways[0] = 1;
		for (int i = 1; i <= n; i++) {
			ways[i] += ways[i - 1];
			for (int j = m; j < i; j++)
				ways[i] += ways[i - j - 1];  // Dynamic programming
			if (i >= m)
				ways[i] += ways[0];
		}
		return ways[n];
	}
	
}
