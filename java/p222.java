/* 
 * Solution to Project Euler problem 222
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p222 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p222().run());
	}
	
	
	public String run() {
		sphereRadii = new double[21];  // {30000, 31000, 32000, ..., 49000, 50000}
		for (int i = 0; i < sphereRadii.length; i++)
			sphereRadii[i] = (i + 30) * 1000;
		minLength = new double[sphereRadii.length][1 << sphereRadii.length];
		
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < sphereRadii.length; i++)
			min = Math.min(findMinimumLength(i, (1 << sphereRadii.length) - 1) + sphereRadii[i], min);
		return Long.toString(Math.round(min));
	}
	
	
	private double[] sphereRadii;  // In micrometres
	
	/* 
	 * minLength[i][j] is the minimum achievable length for fitting a set of spheres in a cylindrical tube
	 * of radius 50000 micrometres, where the sphere of radius sphereRadii[i] is at the left end,
	 * the bit vector j represents the set of spheres, and i must be in the set denoted by j.
	 * (In the integer j, bit k denotes whether the sphere of radius sphereRadii[k] is in the set or not.)
	 * The right-side length of the rightmost sphere is included, the length of the distance between spheres
	 * (arranged in an optimal way) is included, but the left-side length of the leftmost sphere is excluded.
	 * 
	 * For example, minLength[3][0x819] is the minimum length of fitting the set of spheres with radii
	 * {30000, 33000, 34000, 41000} micrometres, where the leftmost sphere has radius 33000
	 * (and this value is discounted from the total length).
	 */
	private double[][] minLength;
	
	private double findMinimumLength(int currentSphereIndex, int setOfSpheres) {
		if ((setOfSpheres & (1 << currentSphereIndex)) == 0)
			throw new IllegalArgumentException();
		
		// Memoization
		if (minLength[currentSphereIndex][setOfSpheres] == 0) {
			double result;
			if (Integer.bitCount(setOfSpheres) == 1)
				result = sphereRadii[currentSphereIndex];  // This sphere is rightmost
			else {
				result = Double.POSITIVE_INFINITY;
				int newSetOfSpheres = setOfSpheres ^ (1 << currentSphereIndex);
				for (int i = 0; i < sphereRadii.length; i++) {  // i is the index of the next sphere
					if ((newSetOfSpheres & (1 << i)) == 0)
						continue;
					// The sqrt() here is what makes the entire computation not guaranteed to be accurate
					double temp = Math.sqrt((sphereRadii[i] + sphereRadii[currentSphereIndex] - 50000) * 200000);
					temp += findMinimumLength(i, newSetOfSpheres);
					result = Math.min(temp, result);
				}
			}
			minLength[currentSphereIndex][setOfSpheres] = result;
		}
		return minLength[currentSphereIndex][setOfSpheres];
	}
	
}
