/* 
 * Solution to Project Euler problem 26
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.HashMap;
import java.util.Map;


public class p026 {
	
	public static void main(String[] args) {
		int bestNumber = -1;
		int bestLength = -1;
		
		for (int i = 1; i <= 1000; i++) {
			int len = getCycleLength(i);
			if (bestLength == -1 || len > bestLength) {
				bestNumber = i;
				bestLength = len;
			}
		}
		
		System.out.println(bestNumber);
	}
	
	
	private static int getCycleLength(int n) {
		Map<Integer,Integer> stateToIter = new HashMap<Integer,Integer>();
		int state = 1;
		int iter = 0;
		while (!stateToIter.containsKey(state)) {
			stateToIter.put(state, iter);
			state = state * 10 % n;
			iter++;
		}
		return iter - stateToIter.get(state);
	}
	
}
