/* 
 * Solution to Project Euler problem 24
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p024 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p024().run());
	}
	
	
	public String run() {
		// Initialize
		int[] array = new int[10];
		for (int i = 0; i < array.length; i++)
			array[i] = i;
		
		// Permute
		for (int i = 0; i < 999999; i++) {
			if (!Library.nextPermutation(array))
				throw new AssertionError();
		}
		
		// Format output
		String ans = "";
		for (int i = 0; i < array.length; i++)
			ans += array[i];
		return ans;
	}
	
}
