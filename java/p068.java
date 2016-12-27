/* 
 * Solution to Project Euler problem 68
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p068 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p068().run());
	}
	
	
	public String run() {
		int[] state = new int[10];
		for (int i = 0; i < state.length; i++)
			state[i] = i + 1;
		
		String max = null;
		do {
			int sum = state[0] + state[5] + state[6];
			if (   state[1] + state[6] + state[7] != sum
			    || state[2] + state[7] + state[8] != sum
			    || state[3] + state[8] + state[9] != sum
			    || state[4] + state[9] + state[5] != sum)
				continue;
			
			int minOuterIndex = -1;
			int minOuter = Integer.MAX_VALUE;
			for (int i = 0; i < 5; i++) {
				if (state[i] < minOuter) {
					minOuterIndex = i;
					minOuter = state[i];
				}
			}
			
			String s = "";
			for (int i = 0; i < 5; i++)
				s += "" + state[(minOuterIndex + i) % 5] + state[(minOuterIndex + i) % 5 + 5] + state[(minOuterIndex + i + 1) % 5 + 5];
			if (s.length() == 16 && (max == null || s.compareTo(max) > 0))
				max = s;
		} while (Library.nextPermutation(state));
		
		if (max == null)
			throw new AssertionError();
		return max;
	}
	
}
