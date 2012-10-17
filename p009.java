/* 
 * Solution to Project Euler problem 9
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p009 {
	
	public static void main(String[] args) {
		for (int a = 1; a < 1000; a++) {
			for (int b = a + 1; b < 1000; b++) {
				int c = 1000 - a - b;
				if (c > b && a * a + b * b == c * c) {
					System.out.println(a * b * c);
					return;
				}
			}
		}
		throw new AssertionError("Not found");
	}
	
}
