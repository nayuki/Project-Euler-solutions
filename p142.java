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
	
	
	private boolean[] isSquare;
	
	
	public String run() {
		int sumLimit = 10;
		// Raise the limit until a sum is found
		while (true) {
			isSquare = new boolean[sumLimit];
			for (int i = 0; i * i < sumLimit; i++)
				isSquare[i * i] = true;
			
			int sum = findSum(sumLimit);
			if (sum != -1) {
				sum = sumLimit;
				break;
			}
			sumLimit *= 10;
		}
		
		// Lower the limit until no sum is found
		while (true) {
			int sum = findSum(sumLimit);
			if (sum == -1)  // No smaller sum found
				return Integer.toString(sumLimit);
			sumLimit = sum;
		}
	}
	
	
	/* 
	 * Finds any sum s = x+y+z such that s < limit, 0 < z < y < x, and these are
	 * perfect squares: x+y, x-y, x+z, x-z, y+z, y-z. Returns -1 if none is found.
	 * 
	 * Suppose we let x + y = a^2 and x - y = b^2, so that they are always square.
	 * Then x = (a^2 + b^2) / 2 and y = (a^2 - b^2) / 2. By ensuring a > b > 0, we have x > y > 0.
	 * Now z < y and z < limit - x - y. Let y + z = c^2, then explicitly check
	 * if x+z, x-z, and y-z are square.
	 */
	private int findSum(int limit) {
		for (int a = 1; a * a < limit; a++) {
			for (int b = a - 1; b > 0; b--) {
				if ((a + b) % 2 != 0)  // Need them to be both odd or both even so that we get integers for x and y
					continue;
				int x = (a * a + b * b) / 2;
				int y = (a * a - b * b) / 2;
				if (x + y + 1 >= limit)  // Because z >= 1
					continue;
				
				int zlimit = Math.min(y, limit - x - y);
				for (int c = Library.sqrt(y) + 1; c * c - y < zlimit; c++) {
					int z = c * c - y;
					if (isSquare[x + z] && isSquare[x - z] && isSquare[y - z])
						return x + y + z;
				}
			}
		}
		return -1;
	}
	
}
