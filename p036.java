/* 
 * Solution to Project Euler problem 36
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p036 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p036().run());
	}
	
	
	public String run() {
		int sum = 0;
		for (int i = 1; i < 1000000; i++) {
			if (Library.isPalindrome(Integer.toString(i, 10)) && Library.isPalindrome(Integer.toString(i, 2)))
				sum += i;
		}
		return Integer.toString(sum);
	}
	
}
