/* 
 * Solution to Project Euler problem 451
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class p451 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p451().run());
	}
	
	
	/* 
	 * Let n be an arbitrary integer such that n >= 3.
	 * When we say that the modular inverse of m modulo n equals m itself,
	 * the formula is m^-1 = m mod n, which is equivalent to m^2 = 1 mod n.
	 * 
	 * We know that if n is prime, then m^2 = 1 mod n has exactly two solutions:
	 * m = 1, n-1. It is easy to verify that these two numbers are solutions.
	 * The equation factorizes as (m - 1)(m + 1) = 0 mod n. Because n is prime,
	 * the numbers form a field, and there are no zero divisors (two arbitrary
	 * non-zero numbers x and y such that xy = 0). Hence 1 and -1 mod n are
	 * the only possible solutions to the equation. (Note that for the excluded
	 * special prime case where n = 2, the solutions 1 and -1 are the same number.)
	 * 
	 * Suppose we can find the smallest prime factor of n quickly. (Note that if n is
	 * prime, then the smallest prime factor is n itself.) This can be achieved by
	 * building a table ahead of time, using a modification of the sieve of Eratosthenes.
	 * 
	 * Suppose that for every n' < n, we know the set of solutions to m^2 = 1 mod n'.
	 * This means whenever we solve the equation for the number n, we save its solutions
	 * in an ever-growing list, so that when we work on the next value of n we can access
	 * all possible smaller solutions. This is essentially an argument by strong induction.
	 * 
	 * Let p be the smallest prime factor of n. If p = n, then the set of
	 * solutions is {1, n - 1}, and we are finished with this value of n.
	 * 
	 * Otherwise p < n, and obviously n is an integer multiple of p. Because we are looking
	 * for values of m such that m^2 = 1 mod n, these candidate m values also must satisfy
	 * m^2 = 1 mod k for any k that divides n (i.e. k is a factor of n). We look at the set
	 * of solutions for the modulus k = n/p, which has already been solved because k < n.
	 * We know that any solution modulo n must be congruent to these solutions modulo k.
	 * Hence we can try to extend and check these old solutions by brute force. Namely, suppose
	 * m' is a solution modulo k. Then we check the sequence m = m' + 0k, m' + 1k, m' + 2k, ...,
	 * m' + (p-1)k modulo n. Because p is usually a small number, this isn't a lot of work to do.
	 */
	
	private static final int LIMIT = 20000000;
	
	private int[] smallestPrimeFactor;
	private IntArrayArray solutions;
	
	
	public String run() {
		// Build table of smallest prime factors
		smallestPrimeFactor = Library.listSmallestPrimeFactors(LIMIT);
		
		// Process every integer in range
		solutions = new IntArrayArray(LIMIT / 2 + 1);  // Uses about 2 GiB of memory
		solutions.append();
		solutions.append();
		solutions.append(1);
		long sum = 0;
		for (int i = 3; i <= LIMIT; i++) {
			int[] sols = getSolutions(i);
			if (i <= LIMIT / 2)
				solutions.append(sols);
			sum += sols[sols.length - 2];  // Second-largest solution
		}
		return Long.toString(sum);
	}
	
	
	// Returns all the solutions (in ascending order) such that
	// for each k, 1 <= k < n and k^2 = 1 mod n.
	private int[] getSolutions(int n) {
		if (smallestPrimeFactor[n] == n)  // n is prime
			return new int[]{1, n - 1};
		else {
			// Note: Changing the ArrayList<Integer> to an implementation
			// based on int[] does not yield meaningful speed improvement
			List<Integer> temp = new ArrayList<>();
			int p = smallestPrimeFactor[n];
			int[] sols = solutions.get(n / p);
			for (int i = 0, inc = n / p; i < n; i += inc) {
				for (int j : sols) {
					int k = i + j;
					if ((long)k * k % n == 1)
						temp.add(k);
				}
			}
			
			// Convert List<Integer> to int[]
			int[] result = new int[temp.size()];
			for (int i = 0; i < result.length; i++)
				result[i] = temp.get(i);
			return result;
		}
	}
	
	
	
	// Conceptually like int[][], but having elements all packed into one int[].
	private static final class IntArrayArray {
		
		private int[] data;
		private int dataLength;
		private int[] starts;
		private int index;
		
		
		public IntArrayArray(int len) {
			data = new int[1];
			dataLength = 0;
			
			starts = new int[len + 1];
			Arrays.fill(starts, -1);
			starts[0] = 0;
			index = 0;
		}
		
		
		public int[] get(int i) {
			return Arrays.copyOfRange(data, starts[i], starts[i + 1]);
		}
		
		
		public void append(int... arr) {
			while (dataLength + arr.length > data.length)
				data = Arrays.copyOf(data, data.length * 2);
			
			System.arraycopy(arr, 0, data, dataLength, arr.length);
			dataLength += arr.length;
			
			index++;
			starts[index] = dataLength;
		}
		
	}
	
}
