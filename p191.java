/* 
 * Solution to Project Euler problem 191
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p191 {
	
	public static void main(String[] args) {
		long[][][] dp = new long[31][3][2];  // dp[i][j][k] is the number of prize strings of exactly length i with exactly j absences at the tail and exactly k lates in the whole string
		
		dp[0][0][0] = 1;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				for (int k = 0; k < dp[i][j].length; k++) {
					int sum = 0;
					
					// Select which state to append to the tail:
					
					// On time
					if (j == 0)
						sum += dp[i - 1][0][k] + dp[i - 1][1][k] + dp[i - 1][2][k];
					
					// Late
					if (j == 0 && k == 1)
						sum += dp[i - 1][0][0] + dp[i - 1][1][0] + dp[i - 1][2][0];
					
					// Absent
					if (j == 1)
						sum += dp[i - 1][0][k];
					if (j == 2)
						sum += dp[i - 1][1][k];
					
					dp[i][j][k] = sum;
				}
			}
		}
		
		System.out.println(dp[30][0][0] + dp[30][0][1] + dp[30][1][0] + dp[30][1][1] + dp[30][2][0] + dp[30][2][1]);
	}
	
}
