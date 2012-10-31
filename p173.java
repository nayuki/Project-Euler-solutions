/* 
 * Solution to Project Euler problem 173
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p173 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p173().run());
	}
	
	
	public String run() {
		int count = 0;
		for (int a = 3; a <= 1000000; a++) {  // Outer square length
			for (int b = a - 2; b >= 1; b -= 2) {  // Inner square length
				long tiles = (long)a * a - (long)b * b;
				if (tiles > 1000000)
					break;
				count++;
			}
		}
		return Integer.toString(count);
	}
	
}
