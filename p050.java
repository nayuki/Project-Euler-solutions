/* 
 * Solution to Project Euler problem 50
 * By Nayuki Minase
 */


public class p050 {
	
	private static boolean[] isPrime = Library.listPrimality(999999);
	
	
	public static void main(String[] args) {
		int count;
		for (count = 0; sumOfConsecutivePrimes(0, count + 1) < 1000000; count++);
		
		outer:
		for (; count >= 0; count--) {
			int offset;
			for (offset = 0; sumOfConsecutivePrimes(offset + 1, count) < 1000000; offset++);
			
			for (; offset >= 0; offset--) {
				if (isPrime[sumOfConsecutivePrimes(offset, count)]) {
					System.out.println(sumOfConsecutivePrimes(offset, count));
					break outer;
				}
			}
		}
	}
	
	
	private static int sumOfConsecutivePrimes(int offset, int count) {
		int i = 0;
		for (; offset > 0; offset--) {
			for (; !isPrime[i]; i++);
			i++;
		}
		
		int sum = 0;
		for (; count > 0; count--) {
			for (; !isPrime[i]; i++);
			sum += i;
			i++;
		}
		return sum;
	}
	
}
