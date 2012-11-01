/* 
 * Solution to Project Euler problem 145
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p145 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p145().run());
	}
	
	
	public String run() {
		int count = 0;
		for (int digits = 1; digits <= 9; digits++) {
			int[] num = new int[digits];  // In base 10, little-endian
			num[num.length - 1] = 1;      // Initialize to 10^(digits-1)
			
			// Iterate until 10^digits - 1
			do {
				// Skip if the reverse will have leading zeros
				if (num[0] != 0 && isReversible(num))
					count++;
			} while(increment(num));
		}
		return Integer.toString(count);
	}
	
	
	private static boolean[] IS_DIGIT_ODD = {false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true};
	
	// Find the sum of num and reverse(num) digit by digit, checking for even digits. Discard the sum.
	private static boolean isReversible(int[] num) {
		int carry = 0;
		for (int i = 0; i < num.length; i++) {
			int digit = num[i] + num[num.length - 1 - i] + carry;  // In the range 0 to 19 (inclusive)
			if (!IS_DIGIT_ODD[digit])
				return false;
			carry = digit < 10 ? 0 : 1;
		}
		// Ignore the final carry-out because it is either 0 (a leading 0 to be ignored) or 1 (which is odd)
		return true;
	}
	
	
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
