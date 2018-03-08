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
		for (int sum = 0; ; sum++) {
			SpecialSumSet set = SpecialSumSet.makeSet(TARGET_SIZE, sum);
			if (set != null) {
				String ans = "";
				for (int x : set.values)
					ans += x;
				return ans;
			}
		}
	}
	
	
	
	private static final class SpecialSumSet {
		
		public static SpecialSumSet makeSet(int targetSize, int maximumSum) {
			return makeSet(new SpecialSumSet(), targetSize, maximumSum, 1);
		}
		
		
		private static SpecialSumSet makeSet(SpecialSumSet set, int sizeRemain, int sumRemain, int startVal) {
			if (sizeRemain == 0)
				return set;
			if (sizeRemain >= 2 && startVal * sizeRemain >= sumRemain)
				return null;
			
			int endVal = sumRemain;
			if (set.values.length >= 2)
				endVal = Math.min(set.values[0] + set.values[1], endVal);
			for (int val = startVal; val <= endVal; val++) {
				SpecialSumSet temp = set.add(val);
				if (temp == null)
					continue;
				temp = makeSet(temp, sizeRemain - 1, sumRemain - val, val + 1);
				if (temp != null)
					return temp;
			}
			return null;
		}
		
		
		public int[] values;
		
		private boolean[] sumPossible;
		private int[] minimumSum;
		private int[] maximumSum;
		
		
		public SpecialSumSet() {
			this(new int[]{}, new boolean[]{true}, new int[]{0}, new int[]{0});
		}
		
		
		private SpecialSumSet(int[] vals, boolean[] sumPosb, int[] minSum, int[] maxSum) {
			values = vals;
			sumPossible = sumPosb;
			minimumSum = minSum;
			maximumSum = maxSum;
		}
		
		
		public SpecialSumSet add(int val) {
			if (val <= 0)
				throw new IllegalArgumentException();
			int size = values.length;
			if (size >= 1 && val <= values[size - 1])
				throw new IllegalArgumentException();
			
			for (int i = val; i < sumPossible.length; i++) {
				if (sumPossible[i] & sumPossible[i - val])
					return null;
			}
			
			int newSize = size + 1;
			int[] newMin = Arrays.copyOf(minimumSum, newSize + 1);
			int[] newMax = Arrays.copyOf(maximumSum, newSize + 1);
			newMin[newSize] = Integer.MAX_VALUE;
			newMax[newSize] = Integer.MIN_VALUE;
			for (int i = newSize; i >= 1; i--) {
				newMin[i] = Math.min(newMin[i - 1] + val, newMin[i]);
				newMax[i] = Math.max(newMax[i - 1] + val, newMax[i]);
			}
			for (int i = 0; i < newSize; i++) {
				if (newMax[i] >= newMin[i + 1])
					return null;
			}
			
			boolean[] newPosb = Arrays.copyOf(sumPossible, sumPossible.length + val);
			for (int i = newPosb.length - 1; i >= val; i--)
				newPosb[i] |= newPosb[i - val];
			
			int[] newVals = Arrays.copyOf(values, newSize);
			newVals[size] = val;
			return new SpecialSumSet(newVals, newPosb, newMin, newMax);
		}
		
	}
	
}
