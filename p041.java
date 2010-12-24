public class p041 {
	
	public static void main(String[] args) {
		for (int n = 9; n >= 1; n--) {
			int[] digits = new int[n];
			for (int i = 0; i < digits.length; i++)
				digits[i] = i + 1;
			
			int result = -1;
			do {
				if (isPrime(toInteger(digits)))
					result = toInteger(digits);
			} while (nextPermutation(digits));
			if (result != -1) {
				System.out.println(result);
				break;
			}
		}
	}
	
	
	private static boolean nextPermutation(int[] a) {
		int i, n = a.length;
		for (i = n - 2; ; i--) {
			if (i < 0)
				return false;
			if (a[i] < a[i + 1])
				break;
		}
		for (int j = 1; i + j < n - j; j++) {
			int tp = a[i + j];
			a[i + j] = a[n - j];
			a[n - j] = tp;
		}
		int j;
		for (j = i + 1; a[j] <= a[i]; j++);
		int tp = a[i];
		a[i] = a[j];
		a[j] = tp;
		return true;
	}
	
	
	private static boolean isPrime(int x) {
		if (x <= 1)
			return false;
		else {
			for (int i = 2, end = sqrt(x); i <= end; i ++) {
				if (x % i == 0)
					return false;
			}
			return true;
		}
	}
	
	
	private static int sqrt(int x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		int y = 0;
		for (int i = 32768; i != 0; i >>>= 1) {
			y |= i;
			if (y > 46340 || y * y > x)
				y ^= i;
		}
		return y;
	}
	
	
	private static int toInteger(int[] digits) {
		int result = 0;
		for (int x : digits)
			result = result * 10 + x;
		return result;
	}
	
}
