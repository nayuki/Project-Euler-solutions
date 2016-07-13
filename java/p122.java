/* 
 * Solution to Project Euler problem 122
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class p122 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p122().run());
	}
	
	
	/* 
	 * This problem and solution use the concept of addition chains. See these for background information:
	 * http://en.wikipedia.org/wiki/Addition_chain ; https://en.wikipedia.org/wiki/Addition-chain_exponentiation
	 * The high-level idea implemented here is to explore different addition chains by brute force using depth-
	 * first search, and to prune the exploration of chains that are longer than the best chain already found.
	 * 
	 * Definition: A chain is a (mutable) list of integers starting with 1, in strictly ascending order, where each
	 * element (except 1) is the sum of two elements (not necessarily distinct) that occurred earlier in the list.
	 * For example: [1], [1,2], [1,2,4,8,10] are valid chains; [], [3], [1,1], [1,4,2], [1,5] are invalid.
	 * 
	 * The centerpiece of the solution algorithm is a recursive function (i.e. exploreChains()) that
	 * receives a chain, performs some operations, and recurses on a longer chain. Here's how it works:
	 * 
	 * - For the purposes of illustration, suppose that our chain is [1,2,4,8,10].
	 * - Clearly this chain is a valid way to reach the sum of 10, and it took 4 operations to do so (length - 1).
	 * - We examine the array of shortest found chain lengths. If minChainLength[10] > 4, we set this array element to 4.
	 * - However if minChainLength[10] < 4, then we stop this branch of the DFS because there was a better way
	 *   to reach the sum of 10. There is no rigorous mathematical justification for this search pruning though.
	 * 
	 * - Now we make various choices to append one element to the chain and recurse on it.
	 * - Because of the pruning criterion above, we can't add a number less than 10 into the chain (its search
	 *   would get pruned immediately). So we need to add numbers that are greater than 10, and of course
	 *   not greater than the problem's limit of 200.
	 * - When we choose a pair of numbers to add together, we start at the end of the list and work backward to the front.
	 *   This way, we try larger pairs first, such as 10+10=20, then 8+10=18, 8+8=16, 4+10=14, etc. until 1+1 = 2.
	 *   By trying large sums first, this means more of the later recursions with smaller numbers will get pruned.
	 */
	
	private static final int LIMIT = 200;
	
	private int[] minChainLength;
	
	
	public String run() {
		// Set up an array of the shortest chain lengths found so far
		minChainLength = new int[LIMIT + 1];
		Arrays.fill(minChainLength, Integer.MAX_VALUE);
		minChainLength[0] = 0;
		
		// Explore starting from the base case
		List<Integer> temp = new ArrayList<>();
		temp.add(1);
		exploreChains(temp);
		
		// Add up the results
		int sum = 0;
		for (int x : minChainLength)
			sum += x;
		return Integer.toString(sum);
	}
	
	
	// The recursive function that builds up chains and compares them to the shortest length.
	private void exploreChains(List<Integer> chain) {
		int largest = chain.get(chain.size() - 1);
		if (chain.size() - 1 > minChainLength[largest])
			return;
		minChainLength[largest] = chain.size() - 1;
		
		for (int i = chain.size() - 1; i >= 0; i--) {
			for (int j = i; j >= 0; j--) {
				int next = chain.get(i) + chain.get(j);
				if (largest < next && next <= LIMIT) {
					// We manipulate the list in place instead of creating a new one
					chain.add(next);
					exploreChains(chain);
					chain.remove(chain.size() - 1);
				}
			}
		}
	}
	
}
