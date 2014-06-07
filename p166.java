/* 
 * Solution to Project Euler problem 166
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p166 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p166().run());
	}
	
	
	/* 
	 * Give variables to the grid cells like this:
	 *   a b c d
	 *   e f g h
	 *   i j k l
	 *   m n o p
	 * 
	 * Let the sum of any line be s = a + b + c + d = e + f + g + h = etc. = a + f + k + p = etc.
	 * Now do some algebra:
	 *    s = d + h + l + p
	 *      = (s - a - b - c) + (s - e - f - g) + (s - i - j - k) + (s - a - f - k)
	 *      = 4s + a + b + c + e + f + g + i + j + k + a + f + k.
	 *   3s = 2a + b + c + e + 2f + g + i + j + 2k.
	 * Therefore the right-hand side of the last equation must be a multiple of 3.
	 */
	public String run() {
		int count = 0;
		int[] num = new int[9];  // A counter in base 10, little-endian
		do {
			/* 
			 * Grid guide:
			 *   0 1 2 a
			 *   3 4 5 b
			 *   6 7 8 c
			 *   d e f g
			 */
			int sum = num[0]*2 + num[1] + num[2] + num[3] + num[4]*2 + num[5] + num[6] + num[7] + num[8]*2;
			if (sum % 3 != 0) continue;
			sum /= 3;  // The sum of each line
			int a = sum - num[0] - num[1] - num[2];  if (a < 0 || a > 9) continue;
			int b = sum - num[3] - num[4] - num[5];  if (b < 0 || b > 9) continue;
			int c = sum - num[6] - num[7] - num[8];  if (c < 0 || c > 9) continue;
			int d = sum - num[0] - num[3] - num[6];  if (d < 0 || d > 9) continue;
			int e = sum - num[1] - num[4] - num[7];  if (e < 0 || e > 9) continue;
			int f = sum - num[2] - num[5] - num[8];  if (f < 0 || f > 9) continue;
			int g = sum - d - e - f;  if (g < 0 || g > 9) continue;
			if (a + num[5] + num[7] + d != sum || num[0] + num[4] + num[8] + g != sum) continue;
			// No need to test 'a + b + c + g == sum' because it's algebraically true
			count++;
		} while (increment(num));
		return Integer.toString(count);
	}
	
	
	// Increments the given base-10 counter in little endian, e.g. {9, 5, 1} -> {0, 6, 1}.
	// Returns true if incremented, otherwise returns false if all elements are already 9.
	private static boolean increment(int[] num) {
		int i = 0;
		while (num[i] == 9) {
			num[i] = 0;
			i++;
			if (i == num.length)
				return false;
		}
		num[i]++;
		return true;
	}
	
}
