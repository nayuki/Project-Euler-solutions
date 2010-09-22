public class p214 {
	
	public static void main(String[] args) {
		int n = 40000000;
		boolean[] isPrime = listPrimality(n + 1);
		byte[] totientChainLength = new byte[n + 1];
		
		totientChainLength[0] = 0;
		for (int i = 1; i < totientChainLength.length; i++) {
			if(i%100000==0)System.out.println(i);
			int val = totientChainLength[totient(i)] + 1;
			if (val < 0)
				throw new ArithmeticException("Overflow");
			totientChainLength[i] = (byte)val;
		}
		
		long sum = 0;
		for (int i = 0; i < isPrime.length; i++) {
			if (isPrime[i] && totientChainLength[i] == 25)
				sum += i;
		}
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
	
	
	private static int totient(int x) {
		if (x <= 0)
			throw new IllegalArgumentException("Totient of non-positive integer");
		int p = 1;
		for (int i = 2, end = sqrt(x); i <= end; i++) {
			if (x % i == 0) {
				p *= i - 1;
				x /= i;
				while (x % i == 0) {
					p *= i;
					x /= i;
				}
				end = sqrt(x);
			}
		}
		if (x != 1)
			p *= x - 1;
		return p;
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
