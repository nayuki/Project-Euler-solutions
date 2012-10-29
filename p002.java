/* 
 * Solution to Project Euler problem 2
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p002 {
	
	public static void main(String[] args) {
		int sum = 0;
		for (int i = 0; ; i++) {
			int fib = fibonacci(i);
			if (fib > 4000000)
				break;
			if (fib % 2 == 0)
				sum += fib;
		}
		System.out.println(sum);
	}
	
	
	private static int fibonacci(int x) {
		if (x < 0 || x > 46)
			throw new IllegalArgumentException();
		int a = 0;
		int b = 1;
		for (int i = 0; i < x; i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		return a;
	}
	
}
