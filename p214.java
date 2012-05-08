/* 
 * Solution to Project Euler problem 214
 * By Nayuki Minase
 */


public class p214 {
	
	public static void main(String[] args) {
		int n = 40000000;
		boolean[] isPrime = Library.listPrimality(n);
		int[] totient = Library.listTotients(n, isPrime);
		
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
	
}
