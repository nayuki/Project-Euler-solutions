/* 
 * Solution to Project Euler problem 39
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p039 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p039().run());
	}
	
	
	public String run() {
		int maxPerimeter = 0;
		int maxTriangles = 0;
		for (int p = 1; p <= 1000; p++) {
			int triangles = countSolutions(p);
			if (triangles > maxTriangles) {
				maxTriangles = triangles;
				maxPerimeter = p;
			}
		}
		return Integer.toString(maxPerimeter);
	}
	
	
	private static int countSolutions(int p) {
		int count = 0;
		for (int a = 1; a <= p; a++) {
			for (int b = a; b <= p; b++) {
				int c = p - a - b;
				if (b <= c && a * a + b * b == c * c)
					count++;
			}
		}
		return count;
	}
	
}
