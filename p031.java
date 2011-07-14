/* 
 * Solution to Project Euler problem 31
 * By Nayuki Minase
 */


public class p031 {
	
	public static void main(String[] args) {
		int total = 200;
		int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
		
		// Knapsack problem
		int[][] ways = new int[coins.length + 1][total + 1];
		ways[0][0] = 1;
		for (int i = 0; i < coins.length; i++) {
			for (int j = 0; j <= total; j++) {
				for (int k = j; k <= total; k += coins[i])
					ways[i + 1][k] += ways[i][j];  // Dynamic programming
			}
		}
		System.out.println(ways[coins.length][total]);
	}
	
}
