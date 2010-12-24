public class p050 {
	
	private static boolean[] isPrime = listPrimality(999999);
	
	
	public static void main(String[] args) {
		int count;
		for (count = 0; sumOfConsecutivePrimes(0, count + 1) < 1000000; count++);
		
		outer:
		for (; count >= 0; count--) {
			int offset;
			for (offset = 0; sumOfConsecutivePrimes(offset + 1, count) < 1000000; offset++);
			
			for (; offset >= 0; offset--) {
				if (isPrime[sumOfConsecutivePrimes(offset, count)]) {
					System.out.println(sumOfConsecutivePrimes(offset, count));
					break outer;
				}
			}
		}
	}
	
	
	private static int sumOfConsecutivePrimes(int offset, int count) {
		int i = 0;
		for (; offset > 0; offset--) {
			for (; !isPrime[i]; i++);
			i++;
		}
		
		int sum = 0;
		for (; count > 0; count--) {
			for (; !isPrime[i]; i++);
			sum += i;
			i++;
		}
		return sum;
	}
	
	
	private static boolean[] listPrimality(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		boolean[] prime = new boolean[n + 1];
		if (n >= 2)
			prime[2] = true;
		for (int i = 3; i <= n; i += 2)
			prime[i] = true;
		for (int i = 3, end = sqrt(n); i <= end; i += 2) {
			if (prime[i]) {
				for (int j = i * 3; j <= n; j += i << 1)
					prime[j] = false;
			}
		}
		return prime;
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
	
}
