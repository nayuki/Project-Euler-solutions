/* 
 * Solution to Project Euler problem 74
 * By Nayuki Minase
 */


import java.util.HashSet;
import java.util.Set;


public class p074 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 0; i < 1000000; i++) {
			if (getChainLength(i) == 60)
				count++;
		}
		System.out.println(count);
	}
	
	
	private static int getChainLength(int n) {
		Set<Integer> seen = new HashSet<Integer>();
		do {
			seen.add(n);
			n = factorialize(n);
		} while (!seen.contains(n));
		return seen.size();
	}
	
	
	private static int factorialize(int n) {
		int sum = 0;
		for (; n != 0; n /= 10)
			sum += factorial(n % 10);
		return sum;
	}
	
	
	private static int factorial(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		int prod = 1;
		for (int i = 2; i <= n; i++)
			prod *= i;
		return prod;
	}
	
}
