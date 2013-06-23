/* 
 * Solution to Project Euler problem 433
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p433 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p433().run());
	}
	
	
	private static final int LIMIT = 5000000;
	
	
	// Takes several thousand hours of CPU time, but can be modified to be parallelized
	public String run() {
		gcdSteps = new byte[10000][];
		for (int x = 0; x < gcdSteps.length; x++) {
			gcdSteps[x] = new byte[x];
			for (int y = 0; y < x; y++) {
				int temp = gcdSteps(x, y, x);
				if ((byte)temp != temp)
					throw new AssertionError();
				gcdSteps[x][y] = (byte)temp;
			}
		}
		
		long sum = 0;
		for (int x = 1; x <= LIMIT; x++) {
			for (int y = 1; y <= LIMIT; y++)
				sum += gcdSteps(x, y, gcdSteps.length);
		}
		return Long.toString(sum);
	}
	
	
	private byte[][] gcdSteps;
	
	private int gcdSteps(int x, int y, int memoLimit) {
		if (x == y)
			return 1;
		
		int count;
		if (x < y) {
			int z = x;
			x = y;
			y = z;
			count = 1;
		} else
			count = 0;
		
		while (true) {
			if (y == 0)
				return count;
			if (x < memoLimit)
				return count + gcdSteps[x][y];
			int z = x % y;
			x = y;
			y = z;
			count++;
		}
	}
	
}
