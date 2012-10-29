/* 
 * Solution to Project Euler problem 39
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p039 {
	
	public static void main(String[] args) {
		System.out.println(new p039().run());
	}
	
	
	public String run() {
		int maxP = -1;
		int maxTriangles = -1;
		for (int p = 0; p <= 1000; p++) {
			int triangles = countSolutions(p);
			if (maxTriangles == -1 || triangles > maxTriangles) {
				maxTriangles = triangles;
				maxP = p;
			}
		}
		return Integer.toString(maxP);
	}
	
	
	private static int countSolutions(int p) {
		int count = 0;
		for (int a = 1; a <= 1000; a++) {
			for (int b = a; b <= 1000; b++) {
				int c = p - a - b;
				if (b <= c && a * a + b * b == c * c)
					count++;
			}
		}
		return count;
	}
	
}
