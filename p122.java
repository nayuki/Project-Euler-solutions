/* 
 * Solution to Project Euler problem 122
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
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
	
	
	private int[] minChainLength;
	
	
	public String run() {
		minChainLength = new int[201];
		Arrays.fill(minChainLength, 999999);
		minChainLength[0] = 1;
		
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		exploreChains(temp);
		
		int sum = 0;
		for (int x : minChainLength)
			sum += x - 1;
		return Integer.toString(sum);
	}
	
	
	private void exploreChains(List<Integer> chain) {
		int largest = chain.get(chain.size() - 1);
		if (chain.size() > minChainLength[largest])
			return;
		minChainLength[largest] = chain.size();
		
		for (int i = chain.size() - 1; i >= 0; i--) {
			for (int j = i; j >= 0; j--) {
				int next = chain.get(i) + chain.get(j);
				if (next > largest && next <= 200) {
					chain.add(next);
					exploreChains(chain);
					chain.remove(chain.size() - 1);
				}
			}
		}
	}
	
}
