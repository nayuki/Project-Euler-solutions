/* 
 * Solution to Project Euler problem 58
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p058 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p058().run());
	}
	
	
	public String run() {
		int diagonalSize = 1;
		int numPrimes = 0;
		int i = 1;
		while (true) {
			if (Library.isPrime(getSpiralNumber( i,  i))) numPrimes++;
			if (Library.isPrime(getSpiralNumber( i, -i))) numPrimes++;
			if (Library.isPrime(getSpiralNumber(-i,  i))) numPrimes++;
			if (Library.isPrime(getSpiralNumber(-i, -i))) numPrimes++;
			diagonalSize += 4;
			if (numPrimes * 10 < diagonalSize)
				break;
			i++;
		}
		return Integer.toString(i * 2 + 1);
	}
	
	
	private static int getSpiralNumber(int x, int y) {
		if (Math.abs(x) != Math.abs(y))
			throw new UnsupportedOperationException("Only diagonals are supported");
		int n = Math.abs(x) * 2 + 1;
		int n2 = n * n;
		if      (x > 0 && y < 0) return n2 - (n - 1) * 0;
		else if (x < 0 && y < 0) return n2 - (n - 1) * 1;
		else if (x < 0 && y > 0) return n2 - (n - 1) * 2;
		else                     return n2 - (n - 1) * 3;
	}
	
}
