/* 
 * Solution to Project Euler problem 215
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public final class p215 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p215().run());
	}
	
	
	private static final int WIDTH = 32;
	private static final int HEIGHT = 10;
	
	
	public String run() {
		List<int[]> crackPositions = new ArrayList<>();
		getCrackPositions(new Stack<Integer>(), 0, crackPositions);
		
		BigInteger[] ways = new BigInteger[crackPositions.size()];
		Arrays.fill(ways, BigInteger.ONE);
		
		for (int i = 1; i < HEIGHT; i++) {
			BigInteger[] newWays = new BigInteger[ways.length];
			for (int j = 0; j < newWays.length; j++) {
				BigInteger sum = BigInteger.ZERO;
				for (int k = 0; k < ways.length; k++) {
					if (areDisjointSorted(crackPositions.get(j), crackPositions.get(k)))
						sum = sum.add(ways[k]);
				}
				newWays[j] = sum;
			}
			ways = newWays;
		}
		
		BigInteger sum = BigInteger.ZERO;
		for (BigInteger x : ways)
			sum = sum.add(x);
		return sum.toString();
	}


	private static void getCrackPositions(Stack<Integer> cracks, int position, List<int[]> result) {
		if (position < 0)
			throw new IllegalArgumentException();
		else if (position < WIDTH) {
			for (int i = 2; i <= 3; i++) {
				cracks.push(position + i);
				getCrackPositions(cracks, position + i, result);
				cracks.pop();
			}
		} else if (position == WIDTH) {
			int[] temp = new int[cracks.size() - 1];
			for (int i = 0; i < temp.length; i++)
				temp[i] = cracks.get(i);
			result.add(temp);
		} else  // position > WIDTH
			return;
	}
	
	
	private static boolean areDisjointSorted(int[] a, int[] b) {
		for (int i = 0, j = 0; i < a.length && j < b.length; ) {
			if (a[i] == b[j])
				return false;
			else if (a[i] < b[j])
				i++;
			else if (a[i] > b[j])
				j++;
			else
				throw new AssertionError();
		}
		return true;
	}
	
}
