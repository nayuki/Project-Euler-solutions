/* 
 * Solution to Project Euler problem 31
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p031 {
	
	public static void main(String[] args) {
		System.out.println(new p031().run());
	}
	
	
	public String run() {
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
		return Integer.toString(ways[coins.length][total]);
	}
	
}
