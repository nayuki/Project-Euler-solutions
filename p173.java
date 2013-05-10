/* 
 * Solution to Project Euler problem 173
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p173 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p173().run());
	}
	
	
	private static final int TILES = 1000000;
	
	public String run() {
		int count = 0;
		for (int n = 3; n <= TILES / 4 + 1; n++) {  // Outer square length
			for (int k = n - 2; k >= 1; k -= 2) {   // Inner square length
				if ((long)n * n - (long)k * k > TILES)
					break;
				count++;
			}
		}
		return Integer.toString(count);
	}
	
}
