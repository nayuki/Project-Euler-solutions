/* 
 * Solution to Project Euler problem 166
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p166 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p166().run());
	}
	
	
	public String run() {
		int count = 0;
		int[] num = new int[10];  // A counter in base 10, little-endian
		do {
			/* 
			 * Grid guide:
			 * 0 1 2 3
			 * 4 5 6 a
			 * 7 8 9 b
			 * c d e f
			 */
			int sum = num[0] + num[1] + num[2] + num[3];
			int a = sum - num[4] - num[5] - num[6];  if (a < 0 || a > 9) continue;
			int b = sum - num[7] - num[8] - num[9];  if (b < 0 || b > 9) continue;
			int c = sum - num[0] - num[4] - num[7];  if (c < 0 || c > 9) continue;
			int d = sum - num[1] - num[5] - num[8];  if (d < 0 || d > 9) continue;
			int e = sum - num[2] - num[6] - num[9];  if (e < 0 || e > 9) continue;
			int f = sum - c - d - e;  if (f < 0 || f > 9 || f != sum - num[3] - a - b) continue;
			if (num[0] + num[5] + num[9] + f != sum || num[3] + num[6] + num[8] + c != sum) continue;
			count++;
		} while(increment(num));
		return Integer.toString(count);
	}
	
	
	private static boolean increment(int[] num) {
		int i = 0;
		while (num[i] == 9) {
			num[i] = 0;
			i++;
			if (i == num.length)
				return false;
		}
		num[i]++;
		return true;
	}
	
}
