/* 
 * Solution to Project Euler problem 28
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p028 {
	
	public static void main(String[] args) {
		int sum = 1;
		for (int i = 0; i < 500; i++) {  // 500 rings
			int n = i * 2 + 3;
			int n2 = n * n;
			sum += n2 + (n2 - (n - 1)) + (n2 - (n - 1) * 2) + (n2 - (n - 1) * 3);
		}
		System.out.println(sum);
	}
	
}
