/* 
 * Solution to Project Euler problem 103
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p103 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p103().run());
	}
	
	
	private static final int TARGET_SIZE = 7;
	
	public String run() {
		// At the top level, we try to find a special sum set with sum at most s,
		// incrementing the bound s until we succeed. When we find a solution
		// with sum at most s, but find none with sum at most s - 1, it implies
		// that the optimum set's sum is exactly s.
		// Note: A set of n positive integers must have a sum of at least 1 + 2 + 3
		// + ... + n = n * (n + 1) / 2. If the search specifies a maximum sum lower
		// than this, then no solution can exist. But for simplicity we just start
		// searching from a maximum sum of 0.
		for (int sum = 0; ; sum++) {
			SpecialSumSet set = SpecialSumSet.makeSet(TARGET_SIZE, sum);
			if (set != null) {  // Solution found; concatenate numbers into a string
				String ans = "";
				for (int x : set.values)
					ans += x;
				return ans;
			}
		}
	}
	
	
	
	// This helper class represents a finite sequence of distinct positive integers
	// that satisfies properties (i) and (ii) given in the problem statement.
	// Objects of the class are immutable. Objects also keep track of extra data to
	// make it easier to check if adding a new element would violate the properties,
	// without explicitly checking every non-empty disjoint subset pair by brute force.
	private static final class SpecialSumSet {
		
		// Returns the lexicographically lowest special sum set with the given size
		// and with a sum of at most maximumSum, or null if no such set exists.
		public static SpecialSumSet makeSet(int targetSize, int maximumSum) {
			return makeSet(new SpecialSumSet(), targetSize, maximumSum, 1);
		}
		
		
		// Returns the lexicographically lowest special sum set by adding exactly sizeRemain elements
		// to the given set, such that the sum of the additional elements is at most sumRemain,
		// and the next element to be added is at least startVal. Returns null if no such set exists.
		private static SpecialSumSet makeSet(SpecialSumSet set, int sizeRemain, int sumRemain, int startVal) {
			// In summary, this procedure takes a partial answer (prefix) and some parameters,
			// and tries to extend the answer by performing depth-first search through recursion.
			
			if (sizeRemain == 0)  // Base case - success
				return set;
			
			// Optimization: If we still need to add at least 2 elements, then the next element
			// will be at least startVal, the one after will be at least startVal + 1,
			// thereafter is at least startVal + 2, et cetera. The sum of the elements
			// to be added is strictly greater than startVal * sizeRemain, which we can
			// check against sumRemain and bail out early if a solution is impossible.
			if (sizeRemain >= 2 && startVal * sizeRemain >= sumRemain)
				return null;
			
			int endVal = sumRemain;
			// Optimization: If the partial set has at least two elements a and b, then by the
			// property (ii), S({a, b}) = a + b must be greater than any single element of the set.
			// We use the foremost two elements, which have the smallest values - this makes
			// endVal as small and restrictive as possible compared to other choices of elements.
			if (set.values.length >= 2)
				endVal = Math.min(set.values[0] + set.values[1] - 1, endVal);
			
			// Consider each possible value for the next element
			for (int val = startVal; val <= endVal; val++) {
				// Try adding the value and see if any property is violated
				SpecialSumSet temp = set.add(val);
				if (temp == null)
					continue;
				
				// Recurse and see if a solution is found down the call tree
				temp = makeSet(temp, sizeRemain - 1, sumRemain - val, val + 1);
				if (temp != null)
					return temp;
			}
			return null;  // No solution for the given current state
		}
		
		
		// Note: All fields are conceptually immutable
		
		// Positive numbers in strict ascending order. Length 0 or more.
		public int[] values;
		
		// For indexes i from 0 to sum(values) inclusive, sumPossible[i]
		// is true iff there exists a subset of 'values' whose sum is i.
		private boolean[] sumPossible;
		
		// For i from 0 to values.length (inclusive), minimumSum[i] is the minimum sum
		// among all possible subsets of 'values' of size i, and likewise for maximumSum[i].
		private int[] minimumSum;
		private int[] maximumSum;
		
		
		// Creates an empty set, which is a valid state.
		public SpecialSumSet() {
			this(new int[]{}, new boolean[]{true}, new int[]{0}, new int[]{0});
		}
		
		
		// Internal constructor. The contents of the given array objects must not change.
		private SpecialSumSet(int[] vals, boolean[] sumPosb, int[] minSum, int[] maxSum) {
			values = vals;
			sumPossible = sumPosb;
			minimumSum = minSum;
			maximumSum = maxSum;
		}
		
		
		// Attempts to add the given value to this set, returning a new set
		// if successful. Otherwise returns null if any property is violated.
		public SpecialSumSet add(int val) {
			// Simple checks on the value
			if (val <= 0)
				throw new IllegalArgumentException("Value must be positive");
			int size = values.length;
			if (size >= 1 && val <= values[size - 1])
				throw new IllegalArgumentException("Must add values in ascending order");
			
			// Check if adding val to any subset of this set would create a duplicate sum
			for (int i = val; i < sumPossible.length; i++) {
				if (sumPossible[i] & sumPossible[i - val])
					return null;
			}
			
			// Compute minimum and maximum sums for each subset size, with help from old data.
			// The idea is that by introducing the new value val, each subset of the new set
			// either contains val or doesn't. All old subsets are still possible, so we copy
			// the old tables of minima and maxima. Each new subset contains val plus an old subset
			// (possibly empty). Hence we look at the existing minima and maxima, add val to the sum,
			// add 1 to the size, and merge the values into the table of minima and maxima.
			int newSize = size + 1;
			int[] newMin = new int[newSize + 1];
			int[] newMax = new int[newSize + 1];
			for (int i = 1; i < newSize; i++) {
				newMin[i] = Math.min(minimumSum[i], minimumSum[i - 1] + val);
				newMax[i] = Math.max(maximumSum[i], maximumSum[i - 1] + val);
			}
			newMin[newSize] = minimumSum[newSize - 1] + val;
			newMax[newSize] = maximumSum[newSize - 1] + val;
			
			// Check iff property (ii) '|B| > |C| implies S(B) > S(C)' is violated
			for (int i = 0; i < newSize; i++) {
				if (newMax[i] >= newMin[i + 1])
					return null;
			}
			
			// Compute all possible new subset sums, with help from old data. This is the
			// classic table-based algorithm for solving the subset sum or knapsack problem.
			boolean[] newPosb = Arrays.copyOf(sumPossible, sumPossible.length + val);
			for (int i = newPosb.length - 1; i >= val; i--)
				newPosb[i] |= newPosb[i - val];
			
			// Append given value to the new set
			int[] newVals = Arrays.copyOf(values, newSize);
			newVals[size] = val;
			return new SpecialSumSet(newVals, newPosb, newMin, newMax);
		}
		
		/* 
		 * An illustrative example for SpecialSumSet and add():
		 * 
		 * Suppose our current set is {3, 5, 6}. All its subsets and their sums are:
		 * - S({}) = 0.
		 * - S({3}) = 3.
		 * - S({5}) = 5.
		 * - S({6}) = 6.
		 * - S({3, 5}) = 8.
		 * - S({3, 6}) = 9.
		 * - S({5, 6}) = 11.
		 * - S({3, 5, 6}) = 14.
		 * 
		 * Therefore, the data arrays have the following values:
		 * - sumPossible = [T, F, F, T, F, T, T, F, T, T,  F,  T,  F,  F,  T].
		 *   (Sum legend:   0  1  2  3  4  5  6  7  8  9  10  11  12  13  14)
		 * - minimumSums = [0, 3,  8, 14].
		 * - maximumSums = [0, 6, 11, 14].
		 * - (Size legend:  0  1   2   3)
		 * 
		 * Now suppose we want to add the value 7 to the set. Here is what happens:
		 * - First we check that in sumPossible, no pair of 'true' elements are
		 *   separated by a distance of 7. This ensures that if we take any particular
		 *   subset and add 7 to it, its sum won't equal another existing subset sum.
		 * - Let the new set S' = S union {7} = {3, 5, 6, 7}. What are
		 *   the minimum and maximum subset sums for each subset size k?
		 *   - If k = 0, the min and max are both clearly 0.
		 *   - If k = |S| = 4, then min and max are the sum of all elements, thus 21.
		 *   - Otherwise with k > 0, consider an arbitrary subset A of S' where |A| = k.
		 *     - If A does not contain 7, then A is a subset of S, so A's
		 *       minimum sum is minimumSums[k] and A's maximum sum is maximumSums[k].
		 *     - Otherwise split A = {7} union B, where B does not contain 7.
		 *       B is a subset of S, and |B| = k - 1. So A's minimum sum is
		 *       7 + minimumSums[k - 1], and A's maximum sum is 7 + maximumSums[k - 1].
		 *     Hence newMinimumSums[k] = min(minimumSums[k], 7 + minimumSums[k - 1]),
		 *     and newMaximumSums[k] = max(maximumSums[k], 7 + maximumSums[k - 1]).
		 *   For each size k that is not out of bounds, if maximumSums[k] >= minimumSums[k + 1],
		 *   then there exists some set of size k with some set of size k + 1 fails property (ii).
		 *   Otherwise property (ii) is upheld in all subset pairs (including empty subsets).
		 * - Finally, we compute the new sumPossible table (which is guaranteed to
		 *   have no conflicts), and finish creating the new set with the added element.
		 */
	}
	
}
