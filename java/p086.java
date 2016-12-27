/* 
 * Solution to Project Euler problem 86
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class p086 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p086().run());
	}
	
	
	// solutions.get(k) is the set of all solutions where the largest side has length k.
	// A solution is a triple (x, y, z) such that 0 < x <= y <= z, and in the rectangular prism with dimensions x * y * z,
	// the shortest surface path from one vertex to the opposite vertex has an integral length.
	private List<Set<List<Integer>>> solutions = new ArrayList<>();
	
	// cumulativeSolutions.get(m) = solutions.get(0).size() + solutions.get(1).size() + ... + solutions.get(m).size().
	private List<Integer> cumulativeSolutions = new ArrayList<>();
	
	
	public String run() {
		cumulativeSolutions.add(0);
		int limit = 1;
		while (true) {
			// Extend the solutions list with blank sets
			while (solutions.size() < limit)
				solutions.add(new HashSet<List<Integer>>());
			
			generateSolutions(limit);
			
			// Compute the number of cumulative solutions up to and including a certain maximum size
			for (int i = cumulativeSolutions.size(); i < limit; i++) {
				int sum = cumulativeSolutions.get(i - 1) + solutions.get(i).size();
				cumulativeSolutions.add(sum);
				if (sum > 1000000)
					return Integer.toString(i);
			}
			
			// Raise the limit and keep searching
			limit *= 2;
		}
	}
	
	
	// Generates all solutions where the largest side has length less than 'limit'.
	private void generateSolutions(int limit) {
		/* 
		 * Pythagorean triples theorem:
		 *   Every primitive Pythagorean triple with a odd and b even can be expressed as
		 *   a = st, b = (s^2-t^2)/2, c = (s^2+t^2)/2, where s > t > 0 are coprime odd integers.
		 * Now generate all Pythagorean triples, including non-primitive ones.
		 */
		outer:
		for (int s = 3; ; s += 2) {
			for (int t = s - 2; t > 0; t -= 2) {
				if (s * s / 2 >= limit * 3)
					break outer;
				
				if (Library.gcd(s, t) == 1) {
					for (int k = 1; ; k++) {
						int a = s * t * k;
						int b = (s * s - t * t) / 2 * k;
						int c = (s * s + t * t) / 2 * k;
						if (a >= limit && b >= limit)
							break;
						findSplits(a, b, c, limit);
						findSplits(b, a, c, limit);
					}
				}
			}
		}
	}
	
	
	// Assumes that a^2 + b^2 = c^2.
	private void findSplits(int a, int b, int c, int limit) {
		int z = b;
		for (int x = 1; x < a; x++) {
			int y = a - x;
			if (y < x)
				break;
			if (Math.min(Math.min(
					(x + y) * (x + y) + z * z,
					(y + z) * (y + z) + x * x),
					(z + x) * (z + x) + y * y) == c * c) {
				int max = Math.max(Math.max(x, y), z);
				if (max < limit) {
					// Add canonical solution
					List<Integer> soln = new ArrayList<>();
					Collections.addAll(soln, x, y, z);
					Collections.sort(soln);
					solutions.get(max).add(soln);
				}
			}
		}
	}
	
}
