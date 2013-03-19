/* 
 * Solution to Project Euler problem 417
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p417 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p417().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	
	private int[] smallestPrimeFactor;  // Requires 400 MB
	private int[] totients;  // Also requires 400 MB
	
	
	/* 
	 * To calculate the decimal expansion of 1/n digit by digit, we emulate the long division algorithm.
	 * We use a sequence of variables x_0, x_1, etc. to denote the numerical state of the algorithm, starting with x_0 = 1.
	 * To generate the i'th digit, we compute floor(x_i / n). (However, we do not need to do this for this problem.)
	 * To calculate the next state, we compute x_{i+1} = (x_i * 10) mod n. (This is what we will analyze.)
	 * 
	 * We can see that with x_0 = 1, the i'th state is x_i = 10^i mod n.
	 * The decimal expansion cycles if and only if the states are the same at two indexes - in other
	 * words, there are some natural numbers i and j such that x_i = (10^i mod n) = (10^j mod n) = x_j.
	 * 
	 * First, consider the simple case where n is coprime with 10. Then 10 is invertible modulo n,
	 * so we divide by 10^j on both sides, getting 10^{i-j} = 1 mod n. This is our condition for cycling.
	 * By Euler's theorem, 10^totient(n) = 1 mod n, so the decimal expansion is guaranteed to cycle
	 * every totient(n) digits. The actual cycle length is defined to be the smallest positive integer k
	 * such that 10^k = 1 mod n. From group theory, we know that k must divide totient(n);
	 * in other words, k is a factor of totient(n).
	 * 
	 * Otherwise, the harder case is where n is not coprime with 10. Then factorize out all the 2's and 5's
	 * so that n = 2^a * 5^b * m, where a and b are as large as possible, thus m is coprime with 10.
	 * If we get m = 1, then the decimal expansion will terminate, so we exit this case with a cycle length of 0.
	 * For the first max(a, b) states of x_i, the state necessarily does not repeat because x_i keeps gaining
	 * factors of 2 or 5 or both, which it never loses afterward. After this, for i >= max(a, b), we always have
	 * x_i = 0 mod 2^a and x_i = 0 mod 5^b. So for i >= max(a, b), when trying to find cycles, we only need
	 * to consider the behavior of 10^i mod m, which reduces back to the simple case. Therefore, we divide out
	 * all the factors of 2 and 5 from n, and then we apply the same logic as the simple case on m instead of n.
	 * 
	 * To find the smallest positive k such that 10^k = 1 mod n, already knowing that 10^totient(n) = 1 mod n,
	 * first let's take k = totient(n). Now take some p > 1 (not necessarily prime) as a factor of k.
	 * If 10^(k/p) = 1 mod n, then take the new k as k/p. We keep removing factors from k as long as the equation
	 * 10^k = 1 mod n still holds. When all the remaining factors are essential (i.e. removing any one of them
	 * will break the equation), we will have found the smallest satisfactory number k. This is the cycle length.
	 */
	public String run() {
		smallestPrimeFactor = new int[LIMIT + 1];
		for (int i = 2; i < smallestPrimeFactor.length; i++) {
			if (smallestPrimeFactor[i] == 0) {
				smallestPrimeFactor[i] = i;
				if ((long)i * i <= LIMIT) {
					for (int j = i * i; j <= LIMIT; j += i) {
						if (smallestPrimeFactor[j] == 0)
							smallestPrimeFactor[j] = i;
					}
				}
			}
		}
		
		totients = Library.listTotients(LIMIT);
		
		long sum = 0;
		for (int i = 3; i <= LIMIT; i++) {
			sum += getCycleLength(i);
		}
		return Long.toString(sum);
	}
	
	
	private int getCycleLength(int n) {
		while (n % 2 == 0)
			n /= 2;
		while (n % 5 == 0)
			n /= 5;
		if (n == 1)
			return 0;
		
		int result = totients[n];
		int remainingFactors = result;
		while (remainingFactors > 1) {
			int p = smallestPrimeFactor[remainingFactors];
			if (Library.powMod(10, result / p, n) == 1)
				result /= p;
			remainingFactors /= p;
		}
		return result;
	}
	
}
