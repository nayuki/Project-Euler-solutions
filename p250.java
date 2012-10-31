/* 
 * Solution to Project Euler problem 250
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p250 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p250().run());
	}
	
	
	private static final long MODULUS = 10000000000000000L;
	
	
	public String run() {
		// Use dynamic programming
		long[] numSubsets = new long[250];  // numSubsets[i] is {the number of subsets with sum equal to i mod 250} mod 10^16
		numSubsets[0] = 1;
		
		for (int i = 1; i <= 250250; i++) {
			int temp = Library.powMod(i, i, 250);
			long[] newArray = numSubsets.clone();
			for (int j = 0; j < 250; j++)
				newArray[(j + temp) % 250] = (numSubsets[j] + numSubsets[(j + temp) % 250]) % MODULUS;
			numSubsets = newArray;
		}
		
		return Long.toString((numSubsets[0] - 1 + MODULUS) % MODULUS);
	}
	
}
