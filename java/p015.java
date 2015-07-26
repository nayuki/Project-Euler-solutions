/* 
 * Solution to Project Euler problem 15
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p015 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p015().run());
	}
	
	
	public String run() {
		return Library.binomial(40, 20).toString();
	}
	
}
