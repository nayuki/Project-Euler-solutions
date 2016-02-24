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


// Uses the concepts of addition chains. See: http://en.wikipedia.org/wiki/Addition_chain
public final class p122 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p122().run());
	}
	
	
	private static final int LIMIT = 200;
	
	private int[] minChainLength;
	
	
	public String run() {
		minChainLength = new int[LIMIT + 1];
		Arrays.fill(minChainLength, 999999);
		minChainLength[0] = 0;
		
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		exploreChains(temp);
		
		int sum = 0;
		for (int x : minChainLength)
			sum += x;
		return Integer.toString(sum);
	}
	
	
	private void exploreChains(List<Integer> chain) {
		int largest = chain.get(chain.size() - 1);
		if (chain.size() - 1 > minChainLength[largest])
			return;
		minChainLength[largest] = chain.size() - 1;
		
		for (int i = chain.size() - 1; i >= 0; i--) {
			for (int j = i; j >= 0; j--) {
				int next = chain.get(i) + chain.get(j);
				if (largest < next && next <= LIMIT) {
					chain.add(next);
					exploreChains(chain);
					chain.remove(chain.size() - 1);
				}
			}
		}
	}
	
}
