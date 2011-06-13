public class p188 {
	
	public static void main(String[] args) {
		System.out.println(tetrationMod(1777, 1885, 100000000));
	}
	
	
	private static int tetrationMod(int x, int y, int m) {
		if (y == 1)
			return y % m;
		else {
			return powMod(x, tetrationMod(x, y - 1, totient(m)), m);
		}
	}
	
	
	private static int powMod(int x, int y, int m) {
		if (gcd(x, m) != 1)
			throw new IllegalArgumentException();
		y %= totient(m);
		int prod = 1;
		for (int i = 0; i < y; i++)
			prod = (int)((long)x * prod % m);
		return prod;
	}
	
	
	private static int totient(int x) {
		if (x <= 0)
			throw new IllegalArgumentException("Totient of non-positive integer");
		int p = 1;
		for (int i = 2, end = Library.sqrt(x); i <= end; i++) {  // Trial division
			if (x % i == 0) {  // Found a factor
				p *= i - 1;
				x /= i;
				while (x % i == 0) {
					p *= i;
					x /= i;
				}
				end = Library.sqrt(x);
			}
		}
		if (x != 1)
			p *= x - 1;
		return p;
	}
	
	
	private static int gcd(int x, int y) {
		while (y != 0) {
			int z = x % y;
			x = y;
			y = z;
		}
		return x;
	}
	
}
