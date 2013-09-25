/* 
 * Solution to Project Euler problem 91
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p091 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p091().run());
	}
	
	
	private static final int LIMIT = 50;
	
	public String run() {
		int count = 0;
		for (int x1 = 0; x1 <= LIMIT; x1++) {
			for (int y1 = 0; y1 <= LIMIT; y1++) {
				for (int x2 = 0; x2 <= LIMIT; x2++) {
					for (int y2 = 0; y2 <= LIMIT; y2++) {
						if (y2 * x1 < y1 * x2 && isRightTriangle(x1, y1, x2, y2))  // For uniqueness, ensure that (x1,y1) has a larger angle than (x2,y2)
							count++;
					}
				}
			}
		}
		return Integer.toString(count);
	}
	
	
	// Tests whether {(0,0), (x1,y1), (x2,y2)} forms a right triangle.
	private static boolean isRightTriangle(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		return x1*x1 + y1*y1 + x2*x2 + y2*y2 == dx*dx + dy*dy
		    || x1*x1 + y1*y1 + dx*dx + dy*dy == x2*x2 + y2*y2
		    || x2*x2 + y2*y2 + dx*dx + dy*dy == x1*x1 + y1*y1;
	}
	
}
