/* 
 * Solution to Project Euler problem 103
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.List;


public final class p103 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p103().run());
	}
	
	
	private static final int TARGET = 7;
	
	public String run() {
		for (int sum = 0; ; sum++) {
			List<Integer> set = new ArrayList<>();
			if (makeSpecialSumSet(set, 1, sum)) {
				String ans = "";
				for (int x : set)
					ans += x;
				return ans;
			}
		}
	}
	
	
	private static boolean makeSpecialSumSet(List<Integer> set, int start, int remain) {
		if (set.size() == TARGET)
			return true;
		
		// Try adding each possible next element
		for (int i = start; i <= remain; i++) {
			set.add(i);
			if (checkPropertiesWithLastValue(set) && makeSpecialSumSet(set, i + 1, remain - i))
				return true;
			set.remove(set.size() - 1);
		}
		return false;
	}
	
	
	private static boolean checkPropertiesWithLastValue(List<Integer> set) {
		// Unpack into primitives for faster computation
		int size = set.size();
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = set.get(i);
		
		int end = 1 << (size - 1);
		for (int i = end; i < end * 2; i++) {
			int size0 = Integer.bitCount(i);
			int sum0 = 0;
			for (int k = 0; k < size; k++)
				sum0 += ((i >>> k) & 1) * array[k];
			
			for (int j = 1; j < end; j++) {
				if ((i & j) != 0)  // Subsets not disjoint
					continue;
				int size1 = Integer.bitCount(j);
				int sum1 = 0;
				for (int k = 0; k < size - 1; k++)
					sum1 += ((j >>> k) & 1) * array[k];
				if (sum0 == sum1 || size0 < size1 && sum0 > sum1 || size1 < size0 && sum1 > sum0)
					return false;
			}
		}
		return true;
	}
	
}
