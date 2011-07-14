/* 
 * Solution to Project Euler problem 37
 * By Nayuki Minase
 */


public class p037 {
	
	public static void main(String[] args) {
		int count = 0;
		int sum = 0;
		
		int i = 10;
		while (count < 11) {
			if (isTruncatablePrime(i)) {
				count++;
				sum += i;
			}
			i++;
		}
		
		System.out.println(sum);
	}
	
	
	private static boolean isTruncatablePrime(int x) {
		return isRightTruncatablePrime(x) && isLeftTruncatablePrime(x);
	}
	
	
	private static boolean isLeftTruncatablePrime(int x) {
		for (int i = 1000000000; i != 1; i /= 10) {
			if (!Library.isPrime(x % i))
				return false;
		}
		return true;
	}
	
	
	private static boolean isRightTruncatablePrime(int x) {
		for (; x != 0; x /= 10) {
			if (!Library.isPrime(x))
				return false;
		}
		return true;
	}
	
}
