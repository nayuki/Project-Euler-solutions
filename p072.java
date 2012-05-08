/* 
 * Solution to Project Euler problem 72
 * By Nayuki Minase
 */


public class p072 {
	
	public static void main(String[] args) {
		int n = 1000000;
		boolean[] isPrime = Library.listPrimality(n);
		int[] totient = Library.listTotients(n, isPrime);
		long sum = 0;
		for (int i = 2; i < totient.length; i++)
			sum += totient[i];
		System.out.println(sum);
	}
	
}
