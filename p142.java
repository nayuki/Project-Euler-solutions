/* 
 * Solution to Project Euler problem 142
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p142 implements EulerSolution {
	
	
	public static void main(String[] args) {
		System.out.println(new p142().run());
	}
	
	
	private boolean[] isSquare = new boolean[10000001];
	
	
	public String run() {
		for (int i = 0, end = Library.sqrt(isSquare.length - 1); i <= end; i++)
			isSquare[i * i] = true;
		
		int limit = isSquare.length - 1;
		while (true) {
			int temp = findSum(limit);
			if (temp == -1)
				return Integer.toString(limit);
			limit = temp;
		}
	}
	
	
	// Finds any sum s=x+y+z such that s<limit, x>y>z>0, and these are perfect squares: x+y, x-y, x+z, x-z, y+z, y-z. Returns -1 if none is found.
	private int findSum(int limit) {
		// Suppose we let x + y = a^2 and x - y = b^2.
		// Then x = (a^2 + b^2) / 2 and y = (a^2 - b^2) / 2.
		// By ensuring a > b > 0, we have x > y > 0.
		for (int a = 1; a * a <= limit; a++) {
			for (int b = a - 1; b > 0; b--) {
				if ((a + b) % 2 != 0)  // Need them to be the same parity so that we get integers for x and y
					continue;
				int x = (a * a + b * b) / 2;
				int y = (a * a - b * b) / 2;
				if (x + y > limit)
					continue;
				
				for (int z = Math.min(y, limit - x - y) - 1; z > 0; z--) {
					if (isSquare[x + z] && isSquare[x - z] && isSquare[y + z] && isSquare[y - z]) {
						System.out.println(x + " " + y + " " + z);
						return x + y + z;
					}
				}
			}
		}
		return -1;
	}
	
}
