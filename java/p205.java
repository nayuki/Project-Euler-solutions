/* 
 * Solution to Project Euler problem 205
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p205 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p205().run());
	}
	
	
	private static final int[] PYRAMIDAL_DIE_PDF = {0, 1, 1, 1, 1};
	private static final int[] CUBIC_DIE_PDF = {0, 1, 1, 1, 1, 1, 1};
	
	
	public String run() {
		int[] ninePyramidalPdf = {1};
		for (int i = 0; i < 9; i++)
			ninePyramidalPdf = convolve(ninePyramidalPdf, PYRAMIDAL_DIE_PDF);
		
		int[] sixCubicPdf = {1};
		for (int i = 0; i < 6; i++)
			sixCubicPdf = convolve(sixCubicPdf, CUBIC_DIE_PDF);
		
		long numer = 0;
		for (int i = 0; i < ninePyramidalPdf.length; i++)
			numer += (long)ninePyramidalPdf[i] * sum(sixCubicPdf, 0, i);
		long denom = (long)sum(ninePyramidalPdf, 0, ninePyramidalPdf.length) * sum(sixCubicPdf, 0, sixCubicPdf.length);
		return String.format("%.7f", (double)numer / denom);
	}
	
	
	private static int[] convolve(int[] a, int[] b) {
		int[] c = new int[a.length + b.length - 1];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++)
				c[i + j] += a[i] * b[j];
		}
		return c;
	}
	
	
	private static int sum(int[] array, int start, int end) {
		int sum = 0;
		for (int i = start; i < end; i++)
			sum += array[i];
		return sum;
	}
	
}
