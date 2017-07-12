/* 
 * Solution to Project Euler problem 348
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p348 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p348().run());
	}
	
	
	private static final int TARGET_WAYS = 4;
	private static final int TARGET_COUNT = 5;
	
	public String run() {
		for (long limit = 1; ; limit *= 10) {
			if (limit > Integer.MAX_VALUE)
				throw new AssertionError("Overflow");
			long answer = trySearch((int)limit);
			if (answer != -1)
				return Long.toString(answer);
		}
	}
	
	
	@SuppressWarnings("unused")
	private static long trySearch(int limit) {
		if (TARGET_COUNT < 0 || TARGET_WAYS < 0 || TARGET_WAYS > Byte.MAX_VALUE - 1)
			throw new AssertionError();
		byte[] ways = new byte[limit];
		
		for (int i = cbrt(limit - 1); i > 1; i--) {
			int cube = i * i * i;
			for (int j = Library.sqrt(limit - 1 - cube); j > 1; j--) {
				int index = cube + j * j;
				ways[index] = (byte)Math.min(ways[index] + 1, TARGET_WAYS + 1);
			}
		}
		
		long result = 0;
		int count = 0;
		for (int i = 0; i < ways.length; i++) {
			if (ways[i] == TARGET_WAYS && Library.isPalindrome(i)) {
				result += i;
				count++;
				if (count == TARGET_COUNT)
					return result;
			}
		}
		return -1;
	}
	
	
	private static int cbrt(int x) {
		if (x < 0) {
			if (x == -2147483648)
				return -1290;
			else
				return -cbrt(-x);
		}
		int y = 0;
		for (int i = 1 << 10; i != 0; i >>>= 1) {
			y |= i;
			if (y > 1290 || y * y * y > x)
				y ^= i;
		}
		return y;
	}
	
}
