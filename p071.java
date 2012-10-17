/* 
 * Solution to Project Euler problem 71
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p071 {
	
	public static void main(String[] args) {
		int maxN = 0;
		int maxD = 1;
		for (int d = 2; d <= 1000000; d++) {
			int n = d * 3 / 7;
			if (d % 7 == 0)
				n--;
			if ((long)n * maxD > (long)maxN * d) {
				maxN = n;
				maxD = d;
			}
		}
		System.out.println(maxN + "/" + maxD);
	}
	
}
