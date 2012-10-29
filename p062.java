/* 
 * Solution to Project Euler problem 62
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public final class p062 {
	
	public static void main(String[] args) {
		final int LIMIT = 10000;  // Arbitrary search cut-off
		
		Map<Long,Integer> count = new HashMap<Long,Integer>();
		for (int i = 0; i < LIMIT; i++) {
			long numClass = getNumberClass((long)i * i * i);
			int oldCount;
			if (!count.containsKey(numClass)) oldCount = 0;
			else oldCount = count.get(numClass);
			count.put(numClass, oldCount + 1);
		}
		
		int min = -1;
		for (long numClass : count.keySet()) {
			if (count.get(numClass) == 5) {
				for (int i = 0; i < LIMIT; i++) {
					if (getNumberClass((long)i * i * i) == numClass && (min == -1 || i < min))
						min = i;
				}
			}
		}
		System.out.println((long)min * min * min);
	}
	
	
	private static long getNumberClass(long x) {
		char[] digits = Long.toString(x).toCharArray();
		Arrays.sort(digits);
		return Long.parseLong(Library.reverse(new String(digits)));
	}
	
}
