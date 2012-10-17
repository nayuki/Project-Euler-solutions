/* 
 * Solution to Project Euler problem 41
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p041 {
	
	public static void main(String[] args) {
		for (int n = 9; n >= 1; n--) {
			int[] digits = new int[n];
			for (int i = 0; i < digits.length; i++)
				digits[i] = i + 1;
			
			int result = -1;
			do {
				if (Library.isPrime(toInteger(digits)))
					result = toInteger(digits);
			} while (Library.nextPermutation(digits));
			if (result != -1) {
				System.out.println(result);
				break;
			}
		}
	}
	
	
	private static int toInteger(int[] digits) {
		int result = 0;
		for (int x : digits)
			result = result * 10 + x;
		return result;
	}
	
}
