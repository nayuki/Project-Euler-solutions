/* 
 * Solution to Project Euler problem 518
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p518 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p518().run());
	}
	
	
	/* 
	 * Suppose (a+1, b+1, c+1) are three positive integers that form a geometric sequence.
	 * Then by definition, (c+1)/(b+1) = (b+1)/(a+1) = r for some ratio r.
	 * r is a rational number because we are given that a, b, c are integers.
	 * 
	 * Because of this, we can express r = z / y in lowest terms (i.e. y and z are coprime).
	 * Note that since the solution requires a < b < c, we require r > 1, hence z > y > 0.
	 * 
	 * Let's define x = (a+1) / y^2. Then we rearrange to get a + 1 = x * y * y.
	 * We argue that x is an integer. Look at (c+1)/(a+1) = z^2/y^2. y^2 and z^2 are coprime,
	 * thus the simplified denominator y^2 must divide the original denominator of a+1.
	 * 
	 * With {x, y, z} defined, we have b + 1 = x * y * z and c + 1 = x * z * z.
	 * Therefore every possible solution (a+1, b+1, c+1) can be re-expressed as
	 * a triple of integers (x, y, z) such that x >= 1, y >= 1, z > y, and gcd(y,z) = 1.
	 * In fact, this mapping of (a+1, b+1, c+1) to (x, y, z) is unique - this is because
	 * the ratio (b+1)/(a+1) uniquely determines y and z; subsequently a and y together give x.
	 * 
	 * The rest of the algorithm is a matter of searching x, y, z in some ascending order, and stopping
	 * each loop when no more candidates are possible because they all necessarily exceed the limit.
	 */
	
	private static final int LIMIT = Library.pow(10, 8);
	
	public String run() {
		long sum = 0;
		boolean[] isPrime = Library.listPrimality(LIMIT - 1);
		
		// Search all possible x's. Note that a + 1 = x * y * y >= x. Furthermore, a < b, so a + 1 <= b.
		// Thus if x >= LIMIT, then LIMIT <= a + 1 <= b. With b >= LIMIT, no candidates are possible.
		for (int x = 1; x < isPrime.length; x++) {
			
			// Search all possible y's. Notice that when y increases, 'a' strictly increases.
			// So when some y generates an 'a' such that a >= LIMIT, no candidates are possible with higher values of y.
			for (int y = 1; ; y++) {
				long a = (long)x * y * y - 1;
				if (a >= isPrime.length)
					break;
				if (!isPrime[(int)a])
					continue;
				
				// Search all valid z's. We require z > y and gcd(y, z) = 1. Notice that when z increases, c strictly increases.
				// So when some z generates a c such that c >= LIMIT, no candidates are possible with higher values of z.
				for (int z = y + 1; ; z++) {
					if (Library.gcd(y, z) != 1)
						continue;
					long b = (long)x * y * z - 1;
					long c = (long)x * z * z - 1;
					if (c >= isPrime.length)
						break;
					
					// Check whether (a, b, c) is a solution
					if (isPrime[(int)b] && isPrime[(int)c]) {
						long addend = a + b + c;
						if (sum + addend < sum)
							throw new ArithmeticException("Overflow");
						sum += addend;
					}
				}
			}
		}
		return Long.toString(sum);
	}
	
}
