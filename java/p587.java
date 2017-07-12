/* 
 * Solution to Project Euler problem 587
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p587 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p587().run());
	}
	
	
	public String run() {
		double lSectionArea = 1 - Math.PI / 4;
		for (int i = 1; ; i++) {
			double slope = 1.0 / i;
			double a = slope * slope + 1;
			double b = -2 * (slope + 1);
			double c = 1;
			double x = (2 * c) / (-b + Math.sqrt(b * b - 4 * a * c));
			double concaveTriangleArea = x * (1 - Math.sqrt((-x + 2) * x)) / 2;
			concaveTriangleArea += integral(1) - integral(x);
			if (concaveTriangleArea / lSectionArea < 0.001)
				return Integer.toString(i);
			if (i == Integer.MAX_VALUE)
				throw new AssertionError();
		}
	}
	
	
	// The indefinite integral of (1 - sqrt(2x - x^2)) dx.
	private static double integral(double x) {
		double t = x - 1;
		return t - (Math.sqrt(1 - t * t) * t + Math.asin(t)) / 2;
	}
	
}
