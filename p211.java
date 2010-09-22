public class p211 {
	
	public static void main(String[] args) {
		long sum = 0;
		for (int i = 1; i <= 64000000; i++) {
			if(i%100000==0)System.out.println(i);
			if (isPerfectSquare(divisorSquareSum(i)))
				sum += i;
		}
		System.out.println(sum);
	}
	
	
	private static long divisorSquareSum(int n) {
		long sum = 0;
		for (int i = 1, end = (int)sqrt(n); i <= end; i++) {
			if (n % i == 0) {
				sum += (long)i * i;
				sum += (long)(n / i) * (n / i);
			}
		}
		
		if (isPerfectSquare(n))  // Fix-up for perfect square n
			sum -= n;
		
		return sum;
	}


	private static boolean isPerfectSquare(long n) {
		return sqrt(n) * sqrt(n) == n;
	}
	
	
	private static long sqrt(long x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		long y = 0;
		for (long i = 2147483648L; i != 0; i >>>= 1) {
			y |= i;
			if (y > 3037000499L || y * y > x)
				y ^= i;
		}
		return y;
	}

}
