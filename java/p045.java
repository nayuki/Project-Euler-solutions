/* 
 * Solution to Project Euler problem 45
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p045 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p045().run());
	}
	
	
	public String run() {
		int i = 286;
		int j = 166;
		int k = 144;
		while (true) {
			long triangle = (long)i * (i + 1) / 2;
			long pentagon = (long)j * (j * 3 - 1) / 2;
			long hexagon  = (long)k * (k * 2 - 1);
			long min = Math.min(Math.min(triangle, pentagon), hexagon);
			if (min == triangle && min == pentagon && min == hexagon)
				return Long.toString(min);
			if (min == triangle) i++;
			if (min == pentagon) j++;
			if (min == hexagon ) k++;
		}
	}
	
}
