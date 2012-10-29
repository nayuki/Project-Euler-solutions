/* 
 * Solution to Project Euler problem 6
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p006 {
	
	public static void main(String[] args) {
		int sum = 0;
		int sum2 = 0;
		for (int i = 1; i <= 100; i++) {
			sum += i;
			sum2 += i * i;
		}
		System.out.println(sum * sum - sum2);
	}
	
}
