/* 
 * Solution to Project Euler problem 94
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p094 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p094().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 9);
	
	
	/* 
	 * Consider an arbitrary almost equilateral triangle with side lengths (c, c, c +/- 1).
	 * Split it down the middle to get a right triangle, and label the new sides.
	 *     /\               /|
	 *  c /  \ c         c / | b
	 *   /    \    -->    /  |
	 *  --------         -----
	 *   c +/- 1           a
	 * Note that a = (c +/- 1) / 2, and a^2 + b^2 = c^2 (Pythagorean theorem).
	 * 
	 * We know that c is an integer. The area of the original triangle is a*b,
	 * which is an integer by definition from the problem statement.
	 * - If a is an integer, then b is an integer (so that a*b is an integer),
	 *   thus (a,b,c) is a Pythagorean triple.
	 * - Otherwise a is an integer plus a half, then b must be even,
	 *   but a^2 + b^2 is not an integer, which contradicts c being an integer.
	 * 
	 * Conversely, consider an arbitrary Pythagorean triple (a,b,c).
	 * If 2a = c +/- 1, then we can form an almost equilateral triangle:
	 *     /|\
	 *  c / | \ c
	 *   /  |  \
	 *  ---------
	 *      2a
	 * For this to happen, the Pythagorean triple must be primitive. Because if not,
	 * then a = 0 mod k and c = 0 mod k for some k > 1, which means 2a = 0 mod k which
	 * cannot equal c +/- 1 = +/- 1 mod k. So we only need to generate primitive triples.
	 * 
	 * Pythagorean triples theorem:
	 *   Every primitive Pythagorean triple with a odd and b even can be expressed as
	 *   a = st, b = (s^2-t^2)/2, c = (s^2+t^2)/2, where s > t >= 1 are coprime odd integers.
	 */
	public String run() {
		long sum = 0;
		for (int s = 1; s * s <= LIMIT; s++) {
			for (int t = 1; t < s; t++) {
				if (Library.gcd(s, t) == 1) {
					int a = s * t;
					int b = (s * s - t * t) / 2;
					int c = (s * s + t * t) / 2;
					if (a * 2 == c - 1) { int p = c * 3 - 1; if (p <= LIMIT) sum += p; }
					if (a * 2 == c + 1) { int p = c * 3 + 1; if (p <= LIMIT) sum += p; }
					// Swap the roles of a and b and try the same tests
					// Note that a != b, since otherwise c = a * sqrt(2) would be irrational
					if (b * 2 == c - 1) { int p = c * 3 - 1; if (p <= LIMIT) sum += p; }
					if (b * 2 == c + 1) { int p = c * 3 + 1; if (p <= LIMIT) sum += p; }
				}
			}
		}
		return Long.toString(sum);
	}
	
}
