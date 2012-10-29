/* 
 * Solution to Project Euler problem 27
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p027 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p027().run());
	}
	
	
	public String run() {
		int bestNum = -1;
		int bestA = -1;
		int bestB = -1;
		for (int a = -1000; a <= 1000; a++) {
			for (int b = -1000; b <= 1000; b++) {
				int num = numberOfConsecutivePrimesGenerated(a, b);
				if (bestNum == -1 || num > bestNum) {
					bestNum = num;
					bestA = a;
					bestB = b;
				}
			}
		}
		return Integer.toString(bestA * bestB);
	}
	
	
	private static int numberOfConsecutivePrimesGenerated(int a, int b) {
		int i = 0;
		while (isPrime(i * i + i * a + b))
			i++;
		return i;
	}
	
	
	private static boolean isPrime(int x) {
		if (x < 0)
			return false;
		else
			return Library.isPrime(x);
	}
	
}
