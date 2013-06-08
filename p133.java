/* 
 * Solution to Project Euler problem 133
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p133 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p133().run());
	}
	
	
	/* 
	 * R(k) = (10^k - 1) / 9.
	 * R(10^n) = (10^(10^n) - 1) / 9.
	 * 
	 * R(10^n) mod p = 0
	 * <=> (10^(10^n) - 1) / 9 = 0 mod p
	 * <=> 10^(10^n) - 1 = 0 mod 9p
	 * <=> 10^(10^n) = 1 mod 9p.
	 * 
	 * 10^(10^(n+1)) = 10^(10^n * 10) = (10^(10^n))^10.
	 * Therefore 9*R(10^(n+1)) + 1 = (9*R(10^n) + 1)^10.
	 * 
	 * Let f(x) = x^10. For an arbitrary p, consider the infinite sequence (9R(10^0)+1 mod 9p, 9R(10^1)+1 mod 9p, 9R(10^2)+1 mod 9p, ...),
	 * which is equal to (9R(1)+1 mod 9p, f(9R(1)+1) mod 9p, f(f(9R(1)+1)) mod 9p, ...). Note that f(x mod 9p) mod 9p = f(x) mod 9p.
	 * Since the elements of the sequence have at most 9p different values, the sequence must cycle under the iterated application of f.
	 * If 1 is in the sequence, then clearly there exists an n such that R(10^n) is divisible by p.
	 * Otherwise after 9p terms with no 1, there must not be a 1 in the rest of the infinite sequence due to cycling.
	 */
	private static final int LIMIT = Library.pow(10, 5);
	
	public String run() {
		int[] primes = Library.listPrimes(LIMIT);
		int sum = 0;
		for (int p : primes) {
			if (!hasDivisibleRepunit(p))
				sum += p;
		}
		return Integer.toString(sum);
	}
	
	
	// Tests whether there exists an n such that R(10^n) is a multiple of p.
	private static boolean hasDivisibleRepunit(int p) {
		int r = 10 % (p * 9);  // Always equal to 10^(10^i) mod 9p
		for (int i = 0; i < p * 9; i++) {
			if (r == 1)
				return true;
			r = Library.powMod(r, 10, p * 9);
		}
		return false;
	}
	
}
