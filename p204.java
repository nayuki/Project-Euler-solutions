public class p204 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 1; i <= 1000000000; i++) {
			if (isGeneralizedHamming100(i))
				count++;
		}
		System.out.println(count);
	}
	
	
	private static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
	
	
	private static boolean isGeneralizedHamming100(int n) {
		for (int p : primes) {
			while (n % p == 0)
				n /= p;
		}
		return n == 1;
	}
	
}
