/* 
 * Solution to Project Euler problem 109
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class p109 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p109().run());
	}
	
	
	public String run() {
		// Initialization
		points = new ArrayList<Integer>();  // Orderless, but duplicates are important
		for (int i = 1; i <= 20; i++) {
			points.add(i * 1);
			points.add(i * 2);
			points.add(i * 3);
		}
		points.add(25);
		points.add(50);
		
		doublePoints = new ArrayList<Integer>();  // Orderless, but duplicates are important
		for (int i = 1; i <= 20; i++)
			doublePoints.add(i * 2);
		doublePoints.add(25 * 2);
		
		ways = new int[3][101][points.size()];
		for (int[][] x : ways) {
			for (int[] y : x)
				Arrays.fill(y, -1);
		}
		
		int checkouts = 0;
		for (int remainingPoints = 1; remainingPoints < 100; remainingPoints++) {
			for (int throwz = 0; throwz <= 2; throwz++) {
				for (int p : doublePoints) {
					if (p <= remainingPoints)
						checkouts += ways(throwz, remainingPoints - p, points.size() - 1);
				}
			}
		}
		return Integer.toString(checkouts);
	}
	
	
	private List<Integer> points;
	private List<Integer> doublePoints;
	
	private int[][][] ways;
	
	
	// Number of ways to get exactly 'total' points in exactly 'throwz' throws, using
	// items (unordered) from the 'points' list with index less than or equal to 'maxIndex'.
	private int ways(int throwz, int total, int maxIndex) {
		if (ways[throwz][total][maxIndex] == -1) {
			int result;
			if (throwz == 0)
				result = total == 0 ? 1 : 0;
			else {
				result = 0;
				if (maxIndex > 0)
					result += ways(throwz, total, maxIndex - 1);
				if (points.get(maxIndex) <= total)
					result += ways(throwz - 1, total - points.get(maxIndex), maxIndex);
			}
			ways[throwz][total][maxIndex] = result;
		}
		return ways[throwz][total][maxIndex];
	}
	
}
