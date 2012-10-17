/* 
 * Solution to Project Euler problem 7
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p007 {
	
	public static void main(String[] args) {
		boolean[] isPrime = Library.listPrimality(300000);
		for (int i = 0, j = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				j++;
				if (j == 10001) {
					System.out.println(i);
					return;
				}
			}
		}
		throw new RuntimeException("Not found");
	}
	
}
