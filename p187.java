public class p187 {
	
	private static boolean[] isPrime = Library.listPrimality(100000000);
	
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 2; i < 100000000; i++) {
			if (has2PrimeFactors(i))
				count++;
		}
		System.out.println(count);
	}
	
	
	private static boolean has2PrimeFactors(int n) {
		if (isPrime[n])
			return false;
		for (int i = 2, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0)
				return isPrime[n / i];
		}
		throw new AssertionError();
	}
	
}
