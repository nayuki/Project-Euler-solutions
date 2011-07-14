/* 
 * Solution to Project Euler problem 117
 * By Nayuki Minase
 */


public class p117 {
	
	public static void main(String[] args) {
		long[] ways = new long[51];  // Memoization
		ways[0] = 1;
		ways[1] = 1;
		for (int i = 2; i <= 50; i++) {
			for (int j = 1; j <= 4 && j <= i; j++)
				ways[i] += ways[i - j];  // Dynamic programming
		}
		System.out.println(ways[50]);
	}
	
}
