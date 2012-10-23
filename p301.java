/* 
 * Solution to Project Euler problem 301
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p301 {
	
	public static void main(String[] args) {
		long count = 0;
		for (int i = 1; i <= (1 << 30); i++) {
			if ((i ^ (i * 2) ^ (i * 3)) == 0)
				count++;
		}
		System.out.println(count);
	}
	
}
