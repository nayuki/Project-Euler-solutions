/* 
 * Solution to Project Euler problem 90
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p090 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p090().run());
	}
	
	
	public String run() {
		// Each die has (10 choose 6) arrangements, so we have at most 44100 arrangements to check
		int count = 0;
		for (int i = 0; i < (1 << 10); i++) {
			for (int j = i; j < (1 << 10); j++) {  // Ensure i <= j to force the dice to be orderless
				if (Integer.bitCount(i) == 6 && Integer.bitCount(j) == 6 && isArrangementValid(i, j))
					count++;
			}
		}
		return Integer.toString(count);
	}
	
	
	private static int[][] SQUARES = {{0, 1}, {0, 4}, {0, 9}, {1, 6}, {2, 5}, {3, 6}, {4, 9}, {6, 4}, {8, 1}};
	
	
	private static boolean isArrangementValid(int a, int b) {
		if (testBit(a, 6) || testBit(a, 9))
			a |= (1 << 6) | (1 << 9);
		if (testBit(b, 6) || testBit(b, 9))
			b |= (1 << 6) | (1 << 9);
		
		for (int[] sqr : SQUARES) {
			if (!(testBit(a, sqr[0]) && testBit(b, sqr[1]) || testBit(a, sqr[1]) && testBit(b, sqr[0])))
				return false;
		}
		return true;
	}
	
	
	private static boolean testBit(int x, int i) {
		return ((x >>> i) & 1) != 0;
	}
	
}
