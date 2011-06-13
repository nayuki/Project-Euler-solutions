import java.util.Arrays;


public class p014 {
	
	public static void main(String[] args) {
		int maxArg = 1;
		int maxVal = 0;
		for (int i = 1; i < 1000000; i++) {
			if (collatz(i) > maxVal) {
				maxArg = i;
				maxVal = collatz(i);
			}
		}
		System.out.println(maxArg + " " + maxVal);
	}
	
	
	
	private static int[] collatz;  // Memoization
	
	static {
		collatz = new int[10000000];
		Arrays.fill(collatz, -1);
		collatz[1] = 1;
	}
	
	
	private static int collatz(long n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		
		if (n < collatz.length && collatz[(int)n] != -1)
			return collatz[(int)n];  // Return memoized
		else {
			int result;
			if (n % 2 == 0) result = collatz(n / 2) + 1;
			else            result = collatz(checkedAdd(checkedMultiply(n, 3), 1)) + 1;
			
			if (n < collatz.length) collatz[(int)n] = result;  // Memoize
			return result;
		}
	}
	
	
	private static long checkedAdd(long x, long y) {
		long z = x + y;
		if (y > 0 && z < x || y < 0 && z > x)
			throw new ArithmeticException();
		else
			return z;
	}
	
	
	private static long checkedMultiply(long x, long y) {
		if (x <= 0 || y <= 0)
			throw new UnsupportedOperationException("Not implemented");
		
		if (x <= Long.MAX_VALUE / y)
			return x * y;
		else
			throw new ArithmeticException();
	}
	
}
