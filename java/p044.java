/* 
 * Solution to Project Euler problem 44
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p044 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p044().run());
	}
	
	
	public String run() {
		long minD = -1;  // -1 means not found yet, positive number means found a candidate
		// For each upper pentagonal number index, going upward
		for (int i = 2; ; i++) {
			long pentI = pentagonalNumber(i);
			// If the next number down is at least as big as a found difference, then conclude searching
			if (minD != -1 && pentI - pentagonalNumber(i - 1) >= minD)
				break;
			
			// For each lower pentagonal number index, going downward
			for (int j = i - 1; j >= 1; j--) {
				long pentJ = pentagonalNumber(j);
				long diff = pentI - pentJ;
				// If the difference is at least as big as a found difference, then stop testing lower pentagonal numbers
				if (minD != -1 && diff >= minD)
					break;
				else if (isPentagonalNumber(pentI + pentJ) && isPentagonalNumber(diff))
					minD = diff;  // Found a smaller difference
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
		 * If y = pentagonalNumber(x) = x(3x-1) / 2,
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
