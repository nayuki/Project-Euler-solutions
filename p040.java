/* 
 * Solution to Project Euler problem 40
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p040 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p040().run());
	}
	
	
	public String run() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 1000000; i++)
			sb.append(i);
		
		int prod = 1;
		for (int i = 0; i <= 6; i++)
			prod *= sb.charAt(Library.pow(10, i) - 1) - '0';
		return Integer.toString(prod);
	}
	
}
