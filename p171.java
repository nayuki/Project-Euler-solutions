/* 
 * Solution to Project Euler problem 171
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p171 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p171().run());
	}
	
	
	private static final int LENGTH = 20;
	private static final int BASE = 10;
	private static final int MODULUS = Library.pow(10, 9);  // Must be less than 2^31
	
	
	public String run() {
		int MAX_SQR_DIGIT_SUM = (BASE - 1) * (BASE - 1) * LENGTH;
		long[][] sum   = new long[LENGTH + 1][MAX_SQR_DIGIT_SUM + 1];
		long[][] count = new long[LENGTH + 1][MAX_SQR_DIGIT_SUM + 1];
		count[0][0] = 1;
		for (int i = 1; i <= LENGTH; i++) {
			for (int j = 0; j < BASE; j++) {
				for (int k = 0; k + j * j <= MAX_SQR_DIGIT_SUM; k++) {
					sum[i][k + j * j] = (sum[i][k + j * j] + sum[i - 1][k] + Library.powMod(BASE, i - 1, MODULUS) * j * count[i - 1][k]) % MODULUS;
					count[i][k + j * j] = (count[i][k + j * j] + count[i - 1][k]) % MODULUS;
				}
			}
		}
		
		long s = 0;
		for (int i = 1; i * i <= MAX_SQR_DIGIT_SUM; i++)
			s = (s + sum[LENGTH][i * i]) % MODULUS;
		return String.format("%09d", s);
	}
	
}
