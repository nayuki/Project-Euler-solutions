/* 
 * Solution to Project Euler problem 165
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public final class p165 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p165().run());
	}
	
	
	private static final int NUM_LINE_SEGMENTS = 5000;
	
	
	public String run() {
		BbsRandom rand = new BbsRandom();
		List<LineSegment> lines = new ArrayList<>();
		for (int i = 0; i < NUM_LINE_SEGMENTS; i++)
			lines.add(new LineSegment(rand));
		
		Set<Point> trueIntersections = new HashSet<>();
		Fraction FRAC_ONE = new Fraction(bi(1));
		for (int i = 0; i < lines.size(); i++) {
			LineSegment seg0 = lines.get(i);
			for (int j = i + 1; j < lines.size(); j++) {
				LineSegment seg1 = lines.get(j);
				
				int x0 = seg0.x0, y0 = seg0.y0;
				int x1 = seg0.x1, y1 = seg0.y1;
				int x2 = seg1.x0, y2 = seg1.y0;
				int x3 = seg1.x1, y3 = seg1.y1;
				
				// https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line_segment
				int denom = (x0 - x1) * (y2 - y3) - (x2 - x3) * (y0 - y1);
				if (denom == 0) {
					// There is no unique intersection point between the two infinite lines. This is equivalent to
					// {one or both line segments being just a point, or both line segments being parallel
					// (regardless of whether or not they lie on the same infinite line)}.
					continue;
				}
				int numer0 = (x0 - x2) * (y2 - y3) - (x2 - x3) * (y0 - y2);
				int numer1 = (x1 - x0) * (y0 - y2) - (x0 - x2) * (y1 - y0);
				
				Fraction t0 = new Fraction(bi(numer0), bi(denom));
				Fraction t1 = new Fraction(bi(numer1), bi(denom));
				if (Fraction.ZERO.compareTo(t0) < 0 && t0.compareTo(FRAC_ONE) < 0 &&
						Fraction.ZERO.compareTo(t1) < 0 && t1.compareTo(FRAC_ONE) < 0) {
					Point p = new Point(
						frac(x0).add(t0.multiply(frac(x1 - x0))),
						frac(y0).add(t0.multiply(frac(y1 - y0))));
					trueIntersections.add(p);
				}
			}
		}
		return Integer.toString(trueIntersections.size());
	}
	
	
	private static Fraction frac(int x) {
		return new Fraction(bi(x));
	}
	
	
	private static BigInteger bi(int x) {
		return BigInteger.valueOf(x);
	}
	
	
	
	// Blum Blum Shub generator
	private static final class BbsRandom {
		
		private int state = 290797;
		
		
		public int next() {
			state = (int)((long)state * state % 50515093);
			return state % 500;
		}
		
	}
	
	
	
	private static final class LineSegment {
		
		public final int x0, y0, x1, y1;
		
		
		public LineSegment(BbsRandom r) {
			x0 = r.next();
			y0 = r.next();
			x1 = r.next();
			y1 = r.next();
		}
		
	}
	
	
	
	private static final class Point {
		
		private final int xNumer, xDenom, yNumer, yDenom;
		
		
		public Point(Fraction x, Fraction y) {
			xNumer = x.numerator  .intValueExact();
			xDenom = x.denominator.intValueExact();
			yNumer = y.numerator  .intValueExact();
			yDenom = y.denominator.intValueExact();
		}
		
		
		public boolean equals(Object obj) {
			if (!(obj instanceof Point))
				return false;
			Point other = (Point)obj;
			return xNumer == other.xNumer
			    && xDenom == other.xDenom
			    && yNumer == other.yNumer
			    && yDenom == other.yDenom;
		}
		
		
		public int hashCode() {
			return Objects.hash(xNumer, xDenom, yNumer, yDenom);
		}
		
	}
	
}
