/* 
 * Solution to Project Euler problem 179
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p179 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p179().run());
	}
	
	
	public String run() {
		int count = 0;
		for (int i = 2, end = Library.pow(10, 7); i < end; i++) {
			if (countDivisors(i) == countDivisors(i + 1))
				count++;
		}
		return Integer.toString(count);
	}
	
	
	private static int countDivisors(int n) {
		int end = Library.sqrt(n);
		int count = 0;
		for (int i = 1; i <= end; i++) {
			if (n % i == 0)
				count += 2;
		}
		if (end * end == n)  // Perfect square
			count--;
		return count;
	}
	
}
