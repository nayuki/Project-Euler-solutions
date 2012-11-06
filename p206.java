/* 
 * Solution to Project Euler problem 206
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p206 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p206().run());
	}
	
	
	// The major optimization is to do arithmetic in base 10 in the main loop, avoiding division and modulo
	public String run() {
		// Initialize
		long n = 1000000000;  // The pattern is greater than 10^18, so start searching at 10^9
		int[] ndigits = new int[10];  // In base 10, little-endian
		int[] n2digits = new int[19];  // Based on length of pattern
		long temp = n;
		for (int i = 0; i < ndigits.length; i++, temp /= 10)
			ndigits[i] = (int)(temp % 10);
		temp = n * n;
		for (int i = 0; i < n2digits.length; i++, temp /= 10)
			n2digits[i] = (int)(temp % 10);
		
		// Increment and search
		while (!isConcealedSquare(n2digits)) {
			// Since the square ends with 0, the number itself must end with 0
			n += 10;
			add10(ndigits);
			
			// Currently, n2digits = (n - 10)^2. Add 20n - 100 to it, bringing it to n^2.
			add20n(ndigits, n2digits);
			subtract100(n2digits);
		}
		return Long.toString(n);
	}
	
	
	private static boolean isConcealedSquare(int[] digits) {
		for (int i = 1; i <= 9; i++) {  // Scan for 1 to 9
			if (digits[digits.length + 1 - i * 2] != i)
				return false;
		}
		return digits[0] == 0;  // Special case for 0
	}
	
	
	private static void add10(int[] n) {
		int i = 1;
		while (n[i] == 9) {
			n[i] = 0;
			i++;
		}
		n[i]++;
	}
	
	
	private static void add20n(int[] n, int[] n2) {
		int carry = 0;
		int i;
		for (i = 0; i < n.length; i++) {
			int sum = n[i] * 2 + n2[i + 1] + carry;
			n2[i + 1] = sum % 10;
			carry = sum / 10;
		}
		for (i++; carry > 0; i++) {
			int sum = n2[i] + carry;
			n2[i] = sum % 10;
			carry = sum / 10;
		}
	}
	
	
	private static void subtract100(int[] n2) {
		int i = 2;
		while (n2[i] == 0) {
			n2[i] = 9;
			i++;
		}
		n2[i]--;
	}
	
}
