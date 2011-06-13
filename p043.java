public class p043 {
	
	public static void main(String[] args) {
		int[] divisibilityTests = {2, 3, 5, 7, 11, 13, 17};
		
		int[] digits = new int[10];
		for (int i = 0; i < digits.length; i++)
			digits[i] = i;
		
		long sum = 0;
		outer:
		do {
			for (int i = 0; i < divisibilityTests.length; i++) {
				if (toInteger(digits, i + 1, 3) % divisibilityTests[i] != 0)
					continue outer;
			}
			System.out.println(toInteger(digits, 0, digits.length));
			sum += toInteger(digits, 0, digits.length);
		} while (Library.nextPermutation(digits));
		System.out.println("Sum: " + sum);
	}
	
	
	private static long toInteger(int[] digits, int off, int len) {
		long result = 0;
		for (int i = off; i < off + len; i++)
			result = result * 10 + digits[i];
		return result;
	}
	
}
