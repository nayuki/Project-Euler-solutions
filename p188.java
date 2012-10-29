/* 
 * Solution to Project Euler problem 188
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p188 {
	
	public static void main(String[] args) {
		System.out.println(tetrationMod(1777, 1885, Library.pow(10, 8)));
	}
	
	
	private static int tetrationMod(int x, int y, int m) {
		if (y == 1)
			return y % m;
		else {
			return powMod(x, tetrationMod(x, y - 1, Library.totient(m)), m);
		}
	}
	
	
	private static int powMod(int x, int y, int m) {
		if (Library.gcd(x, m) != 1)
			throw new IllegalArgumentException();
		y %= Library.totient(m);
		int prod = 1;
		for (int i = 0; i < y; i++)
			prod = (int)((long)x * prod % m);
		return prod;
	}
	
}
