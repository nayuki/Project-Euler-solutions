public class p072 {
	
	public static void main(String[] args) {
		int n = 1000000;
		boolean[] isPrime = listPrimality(n);
		int[] totient = listTotients(n, isPrime);
		long sum = 0;
		for (int i = 2; i < totient.length; i++)
			sum += totient[i];
		System.out.println(sum);
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
	
	
	private static int[] listTotients(int n, boolean[] isPrime) {
		int[] totients = new int[n + 1];
		totients[1] = 1;
		for (int i = 2; i <= n; i++) {
			if (isPrime[i])
				totients[i] = i - 1;
			else {
				for (int j = 2; ; j++) {  // This loop will terminate before j > sqrt(n)
					if (isPrime[j] && i % j == 0) {  // Only need to consider prime factors
						int tot = j - 1;
						int temp = i / j;
						while (temp % j == 0) {
							tot *= j;
							temp /= j;
						}
						// temp is now coprime to j
						tot *= totients[temp];
						totients[i] = tot;
						break;
					}
				}
			}
		}
		return totients;
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
