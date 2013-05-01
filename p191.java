/* 
 * Solution to Project Euler problem 191
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p191 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p191().run());
	}
	
	
	private static final int NUM_DAYS = 30;
	private static final int MAX_ABSENT = 2;
	private static final int MAX_LATE = 1;
	
	
	public String run() {
		// numPrizeStrings[i][j][k] is the number of prize strings of length i with
		// exactly j absences at the tail and exactly k lates in the whole string
		long[][][] numPrizeStrings = new long[NUM_DAYS + 1][MAX_ABSENT + 1][MAX_LATE + 1];
		numPrizeStrings[0][0][0] = 1;
		for (int i = 1; i <= NUM_DAYS; i++) {
			for (int j = 0; j <= MAX_ABSENT; j++) {
				for (int k = 0; k <= MAX_LATE; k++) {
					// Count which states can be appended to the tail
					long sum;
					if (j == 0) {
						sum = 0;
						for (int l = 0; l <= MAX_ABSENT; l++)
							sum += numPrizeStrings[i - 1][l][k];  // On time
						if (k > 0) {
							for (int l = 0; l <= MAX_ABSENT; l++)
								sum += numPrizeStrings[i - 1][l][k - 1];  // Late
						}
					} else
						sum = numPrizeStrings[i - 1][j - 1][k];  // Absent
					numPrizeStrings[i][j][k] = sum;
				}
			}
		}
		
		long sum = 0;
		for (int j = 0; j <= MAX_ABSENT; j++) {
			for (int k = 0; k <= MAX_LATE; k++)
				sum += numPrizeStrings[NUM_DAYS][j][k];
		}
		return Long.toString(sum);
	}
	
}
