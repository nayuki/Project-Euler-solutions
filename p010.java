/* 
 * Solution to Project Euler problem 10
 * By Nayuki Minase
 */


public class p010 {
	
	public static void main(String[] args) {
		boolean[] isPrime = Library.listPrimality(1999999);
		long sum = 0;
		for (int i = 0; i < isPrime.length; i++) {
			if (isPrime[i])
				sum += i;
		}
		System.out.println(sum);
	}
	
}
