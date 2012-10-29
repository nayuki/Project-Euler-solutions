/* 
 * Solution to Project Euler problem 92
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p092 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 1, end = Library.pow(10, 7); i < end; i++) {
			if (isClass89(i))
				count++;
		}
		System.out.println(count);
	}
	
	
	private static boolean isClass89(int x) {
		while (true) {
			switch (x) {
				case  1:  return false;
				case 89:  return true;
				default:  x = nextNumber(x);
			}
		}
	}
	
	
	private static int nextNumber(int x) {
		int sum = 0;
		while (x != 0) {
			sum += (x % 10) * (x % 10);
			x /= 10;
		}
		return sum;
	}
	
}
