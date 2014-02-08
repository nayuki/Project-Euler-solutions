/* 
 * Solution to Project Euler problem 451
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class p451 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p451().run());
	}
	
	
	private static final int LIMIT = 20000000;
	
	private int[] smallestPrimeFactor;
	private IntArrayArray solutions;
	
	
	public String run() {
		// Build table of smallest prime factors
		smallestPrimeFactor = Library.listSmallestPrimeFactors(LIMIT);
		
		// Process every integer in range
		solutions = new IntArrayArray(LIMIT / 2 + 1);  // Uses about 2 GiB of memory
		solutions.append(new int[]{});
		solutions.append(new int[]{});
		solutions.append(new int[]{1});
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
			List<Integer> temp = new ArrayList<Integer>();
			int p = smallestPrimeFactor[n];
			int[] sols = solutions.get(n / p);
			for (int i = 0, inc = n / p; i < n; i += inc) {
				for (int j : sols) {
					int k = i + j;
					if ((long)k * k % n == 1)
						temp.add(k);
				}
			}
			return toIntArray(temp);
		}
	}
	
	
	private static int[] toIntArray(List<Integer> list) {
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++)
			result[i] = list.get(i);
		return result;
	}
	
	
	
	// Conceptually like int[][], but having elements all packed into one int[]
	private static class IntArrayArray {
		
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
		
		
		public void append(int[] arr) {
			while (dataLength + arr.length > data.length)
				data = Arrays.copyOf(data, data.length * 2);
			
			System.arraycopy(arr, 0, data, dataLength, arr.length);
			dataLength += arr.length;
			
			index++;
			starts[index] = dataLength;
		}
		
	}
	
}
