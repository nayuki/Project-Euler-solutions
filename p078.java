/* 
 * Solution to Project Euler problem 78
 * By Nayuki Minase
 */

import java.util.ArrayList;
import java.util.List;


public class p078 {
	
	private static int MODULUS = 1000000;
	
	
	// Compute and memoize the intermediate partition function, mod one million
	public static void main(String[] args) {
		// p.get(n)[k] == p(n, k) mod 1000000
		List<int[]> p = new ArrayList<int[]>();
		for (int n = 0; ; n++) {
			int[] row = new int[n + 1];
			row[0] = 0;
			if (n >= 1) {
				row[1] = 1;
				int rowSum = 1;
				for (int k = 2; k <= n; k++) {
					int temp = p.get(n - 1)[k - 1];
					if (n - k >= k)
						temp = (temp + p.get(n - k)[k]) % MODULUS;
					row[k] = temp;
					rowSum = (rowSum + temp) % MODULUS;
				}
				
				if (rowSum % MODULUS == 0) {
					System.out.println(n);
					break;
				}
			}
			p.add(row);
		}
	}
	
}
