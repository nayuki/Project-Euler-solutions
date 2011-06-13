public class p023 {
	
	public static void main(String[] args) {
		// Compute look-up table
		boolean[] isAbundant = new boolean[28124];
		for (int i = 1; i < isAbundant.length; i++)
			isAbundant[i] = isAbundant(i);
		
		int sum = 0;
		for (int i = 1; i <= 28123; i++) {
			if (!isSumOf2Abundants(i, isAbundant))
				sum += i;
		}
		System.out.println(sum);
	}
	
	
	private static boolean isSumOf2Abundants(int n, boolean[] isAbundant) {
		for (int i = 0; i <= n; i++) {
			if (isAbundant[i] && isAbundant[n - i])
				return true;
		}
		return false;
	}
	
	
	private static boolean isAbundant(int n) {
		if (n < 1)
			throw new IllegalArgumentException();
		
		int sum = 0;  // Sum of factors
		for (int i = 1; i < n; i++) {
			if (n % i == 0)
				sum += i;
		}
		return sum > n;
	}
	
}
