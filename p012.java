/* 
 * Solution to Project Euler problem 12
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p012 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p012().run());
	}
	
	
	public String run() {
		int num = 0;
		for (int i = 1; ; i++) {
			num += i;  // num is triangle number i
			if (countDivisors(num) > 500)
				return Integer.toString(num);
		}
	}
	
	
	private static int countDivisors(int n) {
		int count = 0;
		int end = Library.sqrt(n);
		for (int i = 1; i < end; i++) {
			if (n % i == 0)
				count += 2;
		}
		if (end * end == n)  // Perfect square
			count++;
		return count;
	}
	
}
