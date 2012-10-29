/* 
 * Solution to Project Euler problem 20
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p020 {
	
	public static void main(String[] args) {
		String temp = Library.factorial(100).toString();
		int sum = 0;
		for (int i = 0; i < temp.length(); i++)
			sum += temp.charAt(i) - '0';
		System.out.println(sum);
	}
	
}
