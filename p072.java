/* 
 * Solution to Project Euler problem 72
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p072 {
	
	public static void main(String[] args) {
		int[] totient = Library.listTotients(Library.pow(10, 6));
		long sum = 0;
		for (int i = 2; i < totient.length; i++)
			sum += totient[i];
		System.out.println(sum);
	}
	
}
