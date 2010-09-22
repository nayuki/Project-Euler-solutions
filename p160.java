public class p160 {
	
	public static void main(String[] args) {
		System.out.println(factorialLast5Digits(1000000000000L));
	}
	
	
	private static int factorialLast5Digits(long n) {
		long x = 1;
		long twos = 0;
		for (long i = 1; i <= n; i++) {
			long j = i;
			for (; j % 2 == 0; twos++, j /= 2);
			for (; j % 5 == 0; twos--, j /= 5);
			
			x = x * j % 100000;
		}
		for (; twos > 0; x = x * 2 % 100000, twos--);
		return (int)x;
	}
	
}
