/* 
 * Solution to Project Euler problem 188
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p188 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p188().run());
	}
	
	
	public String run() {
		return Integer.toString(tetrationMod(1777, 1885, Library.pow(10, 8)));
	}
	
	
	private static int tetrationMod(int x, int y, int m) {
		if (y == 1)
			return y % m;
		else {
			return Library.powMod(x, tetrationMod(x, y - 1, Library.totient(m)), m);
		}
	}
	
}
