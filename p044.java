/* 
 * Solution to Project Euler problem 44
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p044 {
	
	public static void main(String[] args) {
		System.out.println(new p044().run());
	}
	
	
	public String run() {
		for (long i = 1; ; i++) {  // For each pentagonal difference
			long penti = pentagonalNumber(i);
			for (long j = 1; ; j++) {  // For each lower pentagonal number
				long pentj = pentagonalNumber(j);
				if (pentagonalNumber(j + 1) - pentj > penti)
					break;
				if (isPentagonalNumber(pentj + penti) && isPentagonalNumber(pentj * 2 + penti))
					return Long.toString(penti);
			}
		}
	}
	
	
	private static long pentagonalNumber(long n) {
		if (n <= 0 || n > 2479700524L)
			throw new IllegalArgumentException();
		return n * (3 * n - 1) >>> 1;
	}
	
	
	private static boolean isPentagonalNumber(long n) {
		if (n <= 0)
			return false;
		long temp = n * 24 + 1;
		long sqrt = Library.sqrt(temp);
		return sqrt * sqrt == temp && (sqrt + 1) % 6 == 0;
	}
	
}
