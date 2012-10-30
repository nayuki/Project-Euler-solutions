/* 
 * Solution to Project Euler problem 72
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p072 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p072().run());
	}
	
	
	public String run() {
		int[] totient = Library.listTotients(Library.pow(10, 6));
		long sum = 0;
		for (int i = 2; i < totient.length; i++)
			sum += totient[i];
		return Long.toString(sum);
	}
	
}
