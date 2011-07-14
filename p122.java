/* 
 * Solution to Project Euler problem 122
 * By Nayuki Minase
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Uses the concepts of addition chains. See: http://en.wikipedia.org/wiki/Addition_chain
public class p122 {
	
	private static int[] minChainLength;
	
	
	public static void main(String[] args) {
		minChainLength = new int[201];
		Arrays.fill(minChainLength, 999999);
		minChainLength[0] = 1;
		
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		exploreChains(temp);
		
		int sum = 0;
		for (int x : minChainLength)
			sum += x - 1;
		System.out.println(sum);
	}
	
	
	private static void exploreChains(List<Integer> chain) {
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
