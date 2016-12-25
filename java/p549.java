/* 
 * Solution to Project Euler problem 549
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p549 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p549().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	
	
	private int[] smallestDivisibleFactorials;
	
	public String run() {
		smallestDivisibleFactorials = new int[LIMIT + 1];
		for (int i = 2; i < smallestDivisibleFactorials.length; i++) {
			if (smallestDivisibleFactorials[i] == 0) {
				// Now we know that i is prime
				long power = 1;
				middle:
				for (int j = i; ; j += i) {
					for (int temp = j; temp % i == 0; temp /= i) {
						power *= i;
						if (power > LIMIT)
							break middle;
						setMultiples((int)power, j);
					}
				}
			}
		}
		
		long sum = 0;
		for (int x : smallestDivisibleFactorials)
			sum += x;
		return Long.toString(sum);
	}
	
	
	private void setMultiples(int k, int val) {
		for (int i = k; i < smallestDivisibleFactorials.length; i += k)
			smallestDivisibleFactorials[i] = Math.max(val, smallestDivisibleFactorials[i]);
	}
	
}
