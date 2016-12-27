/* 
 * Solution to Project Euler problem 61
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.HashSet;
import java.util.Set;


public final class p061 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p061().run());
	}
	
	
	// numbers[i][j] is the set of figurate numbers of i sides (3 <= i <= 8), having 4 digits, beginning with the 2 digits equal to j
	private Set<Integer>[][] numbers;
	
	
	@SuppressWarnings("unchecked")
	public String run() {
		// Build table of numbers
		numbers = new Set[9][100];
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers[i].length; j++)
				numbers[i][j] = new HashSet<>();
		}
		for (int sides = 3; sides <= 8; sides++) {
			for (int n = 1; ; n++) {
				int num = figurateNumber(sides, n);
				if (num >= 10000)
					break;
				if (num >= 1000)
					numbers[sides][num / 100].add(num);
			}
		}
		
		// Do search
		for (int i = 10; i < 100; i++) {
			for (int num : numbers[3][i]) {
				int temp = findSolutionSum(num, num, 1 << 3, num);
				if (temp != -1)
					return Integer.toString(temp);
			}
		}
		throw new AssertionError("No solution");
	}
	
	
	// Note: sidesUsed is a bit set
	private int findSolutionSum(int begin, int current, int sidesUsed, int sum) {
		if (sidesUsed == 0x1F8) {
			if (current % 100 == begin / 100)
				return sum;
			
		} else {
			for (int sides = 4; sides <= 8; sides++) {
				if (((sidesUsed >>> sides) & 1) != 0)
					continue;
				for (int num : numbers[sides][current % 100]) {
					int temp = findSolutionSum(begin, num, sidesUsed | (1 << sides), sum + num);
					if (temp != -1)
						return temp;
				}
			}
		}
		return -1;
	}
	
	
	private static int figurateNumber(int sides, int n) {
		return n * ((sides - 2) * n - (sides - 4)) / 2;
	}
	
}
