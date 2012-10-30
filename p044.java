/* 
 * Solution to Project Euler problem 44
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p044 {
	
	public static void main(String[] args) {
		System.out.println(new p044().run());
	}
	
	
	public String run() {
		long minD = -1;
		for (int i = 2; ; i++) {  // For each upper pentagonal number index, going upward
			long penti = pentagonalNumber(i);
			if (minD != -1 && penti - pentagonalNumber(i - 1) > minD)  // If the next number down is more than a found difference, then conclude searching
				break;
			for (int j = i - 1; j >= 1; j--) {  // For each lower pentagonal number index, going downward
				long pentj = pentagonalNumber(j);
				long diff = penti - pentj;
				if (minD != -1 && diff >= minD)  // If the difference is at least as great as a found difference, then stop testing lower pentagonal numbers
					break;
				else if (isPentagonalNumber(penti + pentj) && isPentagonalNumber(diff) && (minD == -1 || diff < minD))
					minD = diff;
			}
		}
		return Long.toString(minD);
	}
	
	
	private static long pentagonalNumber(int x) {
		if (x <= 0)
			throw new IllegalArgumentException();
		return (long)x * (x * 3 - 1) >>> 1;
	}
	
	
	private static boolean isPentagonalNumber(long y) {
		if (y <= 0)
			return false;
		
		/* 
		 * If y = pentagonalNumber(x) = x (3x-1) / 2,
		 * then by the quadratic formula, the positive solution is x = (sqrt(24y + 1) + 1) / 6.
		 * There exists a solution for x if and only if both of these conditions hold:
		 * (24y + 1) is a perfect square, and sqrt(24y + 1) + 1 mod 6 = 0.
		 * The second condition is equivalent to sqrt(24y + 1) = 5 mod 6.
		 */
		long temp = y * 24 + 1;
		long sqrt = Library.sqrt(temp);
		return sqrt * sqrt == temp && sqrt % 6 == 5;
	}
	
}
