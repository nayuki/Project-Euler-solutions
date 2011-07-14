/* 
 * Solution to Project Euler problem 214
 * By Nayuki Minase
 */


public class p214 {
	
	public static void main(String[] args) {
		int n = 40000000;
		boolean[] isPrime = Library.listPrimality(n);
		int[] totient = listTotients(n, isPrime);
		
		int[] totientChainLength = new int[n + 1];
		
		totientChainLength[0] = 0;
		for (int i = 1; i < totientChainLength.length; i++) {
			totientChainLength[i] = totientChainLength[totient[i]] + 1;
		}
		
		long sum = 0;
		for (int i = 0; i < isPrime.length; i++) {
			if (isPrime[i] && totientChainLength[i] == 25)
				sum += i;
		}
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
