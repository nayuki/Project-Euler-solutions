/* 
 * Solution to Project Euler problem 122
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p122 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p122().run());
	}
	
	
	/* 
	 * This problem uses the concepts of https://en.wikipedia.org/wiki/Addition_chain
	 * and https://en.wikipedia.org/wiki/Addition-chain_exponentiation .
	 * 
	 * Definition: An addition chain is a finite sequence of integers {a_i} such that:
	 * - a_0 = 1 (i.e. the head element is 1).
	 * - For each index i (with 0 < i < length), there exists indices j and k such that 0 <= j <= k < i
	 *   and a_i = a_j + a_k (i.e. each subsequent element is the sum of some two elements that come before it).
	 * - The number of operations in an addition chain is equal to the length of the chain minus one.
	 * 
	 * Example: {1, 2, 3, 6, 9, 11} is an addition chain because
	 * 2 = 1 + 1, 3 = 1 + 2, 6 = 3 + 3, 9 = 3 + 6, 11 = 2 + 9.
	 * This chain has length 6, and uses 5 addition operations.
	 * 
	 * Note: A star chain or Brauer chain is an addition chain with the stronger condition that for each i > 0,
	 * there exists an index j such that 0 <= j < i and a_i = a_{i-1} + a_j. However, a minimum-length star chain
	 * might be longer than the minimum-length general addition chain. A counterexample is known for 12509;
	 * the shortest addition chain that produces 12509 is shorter than the shortest star chain that produces it.
	 * This is unfortunate because searching star chains is much faster than searching general addition chains.
	 * 
	 * The overall strategy of this solution is to explore all addition chains by brute force using depth-first search.
	 * We start with the base chain of {1}, progressively add elements that are the sum of some two earlier elements,
	 * and backtrack at each stage. No memoization or breadth-first search is performed because the search space is large.
	 * 
	 * An important detail is that we perform depth-limited search of the full search space, with depth = 1, 2, 3, etc.
	 * This gives us the benefit of breadth-first search without its high memory usage - namely, the first time
	 * we visit a sum of n, we can be sure that it has been reached with the smallest possible chain length.
	 * 
	 * A crucial algorithmic optimization is that we only consider addition chains that are strictly increasing.
	 * Clearly there is no benefit to producing a certain term twice within the same sequence (e.g. 2 + 2 = 4 and 1 + 3 = 4).
	 * As for the increasing order, we argue that for every addition chain that isn't strictly increasing, it can be
	 * reordered to one that is strictly increasing. For example, the chain {1, 2, 4, 3} can be reordered to {1, 2, 3, 4}.
	 * This is because if a_i > a_{i+1}, then a_{i+1} can't possibly use a_i as an addend (which are all positive),
	 * and it must have used two terms that are strictly in front of index i. Therefore, exploring only strictly increasing
	 * addition chains will still give us full coverage of the search space.
	 */
	
	private static final int LIMIT = 200;
	
	private int[] minOperations;
	private int numUnknown;
	
	public String run() {
		// Set up initial array of known/unknown minimum operation counts
		minOperations = new int[LIMIT + 1];
		Arrays.fill(minOperations, -1);
		minOperations[0] = 0;
		minOperations[1] = 0;
		numUnknown = LIMIT - 1;
		
		// Perform bounded depth-first search with incrementing depth
		for (int ops = 1; numUnknown > 0; ops++) {
			IntStack chain = new IntStack(ops + 1);
			chain.push(1);
			exploreChains(chain, ops);
		}
		
		// Add up the results
		int sum = 0;
		for (int x : minOperations)
			sum += x;
		return Integer.toString(sum);
	}
	
	
	// Recursively builds up chains and compares them to chain lengths already found.
	private void exploreChains(IntStack chain, int maxOps) {
		// Depth-based termination or early exit
		if (chain.size > maxOps || numUnknown == 0)
			return;
		
		// Try all unordered pairs of values in the current chain
		int max = chain.values[chain.size - 1];  // Peek at top
		for (int i = chain.size - 1; i >= 0; i--) {
			for (int j = i; j >= 0; j--) {
				int x = chain.values[i] + chain.values[j];
				if (x <= max)
					break;  // Early exit due to ascending order
				if (x <= LIMIT) {
					// Append x to the current chain and recurse
					chain.push(x);
					if (minOperations[x] == -1) {
						// For each unique value of x, we set minOperations[x] only once
						// because we do progressive deepening in the depth-first search
						minOperations[x] = chain.size - 1;
						numUnknown--;
					}
					exploreChains(chain, maxOps);
					chain.pop();
				}
			}
		}
	}
	
	
	
	// This implementation exists because Stack<Integer> is unacceptably slow due to integer boxing and such.
	private static final class IntStack {
		
		public int[] values;
		public int size;
		
		
		public IntStack(int capacity) {
			values = new int[capacity];
			size = 0;
		}
		
		
		public void push(int x) {
			if (size >= values.length)
				throw new IllegalStateException();
			values[size] = x;
			size++;
		}
		
		
		public int pop() {
			if (size <= 0)
				throw new IllegalStateException();
			size--;
			return values[size];
		}
		
	}
	
}
