/* 
 * Solution to Project Euler problem 85
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p085 {
	
	public static void main(String[] args) {
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
		System.out.println(bestArea);
	}
	
	
	private static int numberOfRectangles(int m, int n) {
		return (m + 1) * m * (n + 1) * n / 4;
	}
	
}
