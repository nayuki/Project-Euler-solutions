/* 
 * Solution to Project Euler problem 85
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p085 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p085().run());
	}
	
	
	public String run() {
		int bestDiff = -1;
		int bestArea = -1;
		for (int w = 1; w < 2000; w++) {
			for (int h = 1; h < 2000; h++) {
				int diff = Math.abs(numberOfRectangles(w, h) - 2000000);
				if (bestDiff == -1 || diff < bestDiff) {
					bestDiff = diff;
					bestArea = w * h;
				}
			}
		}
		return Integer.toString(bestArea);
	}
	
	
	private static int numberOfRectangles(int m, int n) {
		return (m + 1) * m * (n + 1) * n / 4;
	}
	
}
