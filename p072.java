public class p072 {
	
	public static void main(String[] args) {
		int n = 1000000;
		boolean[] isPrime = Library.listPrimality(n);
		int[] totient = listTotients(n, isPrime);
		long sum = 0;
		for (int i = 2; i < totient.length; i++)
			sum += totient[i];
		System.out.println(sum);
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
	
}
