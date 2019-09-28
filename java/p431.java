/* 
 * Solution to Project Euler problem 431
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p431 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p431().run());
	}
	
	
	private static final double RADIUS = 6;
	private static final double REPOSE = Math.toRadians(40);
	
	
	public String run() {
		double sum = 0;
		for (int i = 20; i <= 25; i++)
			sum += findRootSecant(0, RADIUS, i * i);
		return String.format("%.9f", sum);
	}
	
	
	private static double findRootSecant(double x0, double x1, double val) {
		// Secant method with adaptive precision for numerical integration
		int samples = 10000;
		double y0 = volume(x0, samples);
		double y1 = volume(x1, samples);
		while (Math.abs(x0 - x1) > 1e-12) {
			double x2 = (val - y0) / (y1 - y0) * (x1 - x0) + x0;
			double y2 = volume(x2, samples);
			x0 = x1;
			x1 = x2;
			y0 = y1;
			y1 = y2;
			if (Math.abs(x0 - x1) < 1e-4)
				samples = Math.max(1000000, samples);
			if (Math.abs(x0 - x1) < 1e-6)
				samples = Math.max(10000000, samples);
			if (Math.abs(x0 - x1) < 1e-8)
				samples = Math.max(100000000, samples);
		}
		return x1;
	}
	
	
	private static double volume(double x, int samples) {
		double discVolume = Math.pow(RADIUS + x, 3) / 3 * Math.PI * 2;
		if (x > 0) {
			// Precomputed constants
			double scaler = x * 2 / samples;
			double r2plusx2 = RADIUS * RADIUS + x * x;
			double rec2x = 1 / (x * 2);
			
			// Integrate using midpoint rule with uniform partition size
			double sum = 0;
			for (int i = 0; i < samples; i++) {
				double r = RADIUS - x + (i + 0.5) * scaler;
				double r2 = r * r;
				sum += Math.acos(((r2plusx2 - r2) * rec2x - x) / r) * r2;
			}
			discVolume -= sum * 4 * x / samples;
		}
		return discVolume * Math.tan(REPOSE);
	}
	// test thu nhat cho x0 ngoai vong while
	@Test
	void testFindRootSecant1() {
		final double expected = 3;
		final double actual = findRootSecant(3, 3, 4);
		Assert.assertEquals(expected, actual);
	}

	// test thu 2 cho x0 trong vong while, re nhanh thu nhat
	@Test
	void testFindRootSecant2() {
		final double expected = -4.684585527286632;
		final double actual = findRootSecant(2.99999, 3, 4);
		Assert.assertEquals(expected, actual);
	}

	// test thu 3 cho x0 trong while, re nhanh thu 2
	@Test
	void testFindRootSecant3() {
		final double expected = -4.684585527286632;
		final double actual = findRootSecant(2.9999999, 3, 4);
		Assert.assertEquals(expected, actual);
	}

	// test thu 4 cho x0 trong while, re nhanh thu 3
	@Test
	void testFindRootSecant4() {
		final double expected = -4.684585527286632;
		final double actual = findRootSecant(2.999999999, 3, 4);
		Assert.assertEquals(expected, actual);
	}

	// test thu 5 cho x1 ngoai while
	@Test
	void testFindRootSecant5() {
		final double expected = -4.684585527286632;
		final double actual = findRootSecant(3, 4, 4);
		Assert.assertEquals(expected, actual);
	}

	// test thu 6 cho x1 trong while, re nhanh thu nhat
	@Test
	void testFindRootSecant6() {
		final double expected = -4.684585527286632;
		final double actual = findRootSecant(3, 3.00001, 4);
		Assert.assertEquals(expected, actual);
	}

	// test thu 7 cho x1 trong while, re nhanh thu 2
	@Test
	void testFindRootSecant7() {
		final double expected = -4.684585527286632;
		final double actual = findRootSecant(3, 3.0000001, 4);
		Assert.assertEquals(expected, actual);
	}

	// test thu 8 cho x1 trong while, re nhanh thu nhat
	@Test
	void testFindRootSecant8() {
		final double expected = -4.684585527286632;
		final double actual = findRootSecant(3, 3.000000001, 4);
		Assert.assertEquals(expected, actual);
	}

	// test thu 9 cho val
	@Test
	void testFindRootSecant9() {
		final double expected = -5.999999999997416;
		final double actual = findRootSecant(3, 4, 0);
		Assert.assertEquals(expected, actual);
	}

	// test thu 10 cho val lan 2
	@Test
	void testFindRootSecant10() {
		final double expected = -4.342681616492063;
		final double actual = findRootSecant(3, 4, 8);
		Assert.assertEquals(expected, actual);
	}

	
}
