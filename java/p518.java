/* 
 * Solution to Project Euler problem 518
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class p518 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p518().run());
	}
	
	
	private static final int LIMIT = Library.pow(10, 8);
	
	public String run() {
		long sum = 0;
		boolean[] isPrime = Library.listPrimality(LIMIT - 1);
		for (int b = 2; b < isPrime.length; b++) {
			if (!isPrime[b])
				continue;
			
			List<Integer> factors = getFactors(b + 1);
			Set<Long> foundA = new HashSet<>();
			for (int u : factors) {
				for (int v : factors) {
					if (u <= v)
						continue;
					
					long a = (b + 1L) / u * v - 1;
					long c = (b + 1L) / v * u - 1;
					if (a < isPrime.length && c < isPrime.length && isPrime[(int)a] && isPrime[(int)c] && foundA.add(a)) {
						long addend = a + b + c;
						if (sum + addend < sum)
							throw new ArithmeticException("Overflow");
						sum += addend;
					}
				}
			}
		}
		return Long.toString(sum);
	}
	
	
	private static List<Integer> getFactors(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		List<Integer> result = new ArrayList<>();
		for (int i = 1, end = Library.sqrt(n); i <= end; i++) {
			if (n % i == 0) {
				result.add(i);
				if (i != end)
					result.add(n / i);
			}
		}
		return result;
	}
	
}
