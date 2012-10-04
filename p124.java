/* 
 * Solution to Project Euler problem 124
 * By Nayuki Minase
 */

import java.util.Arrays;


public class p124 {
	
	public static void main(String[] args) {
		int n = 100000;
		
		// Modification of the Sieve of Eratosthenes
		int[] rads = new int[n + 1];
		Arrays.fill(rads, 1);
		for (int i = 2; i <= n; i++) {
			if (rads[i] == 1) {
				for (int j = i; j <= n; j += i)
					rads[j] *= i;
			}
		}
		
		IntPair[] data = new IntPair[n];
		for (int i = 0; i < data.length; i++)
			data[i] = new IntPair(rads[i + 1], i + 1);
		Arrays.sort(data);
		System.out.println(data[10000 - 1].b);
	}
	
	
	
	private static class IntPair implements Comparable<IntPair> {
		
		public final int a;
		public final int b;
		
		
		public IntPair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		
		public int compareTo(IntPair other) {
			if      (a < other.a) return -1;
			else if (a > other.a) return +1;
			else if (b < other.b) return -1;
			else if (b > other.b) return +1;
			else                  return  0;
		}
		
	}
	
}
