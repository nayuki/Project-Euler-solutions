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
			if (!isPrime(x % i))
				return false;
		}
		return true;
	}
	
	
	private static boolean isRightTruncatablePrime(int x) {
		while (x != 0) {
			if (!isPrime(x))
				return false;
			x /= 10;
		}
		return true;
	}
	
	
	private static boolean isPrime(int x) {
		if (x <= 1)
			return false;
		else {
			for (int i = 2, end = sqrt(x); i <= end; i ++) {
				if (x % i == 0)
					return false;
			}
			return true;
		}
	}
	
	
	private static int sqrt(int x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		int y = 0;
		for (int i = 32768; i != 0; i >>>= 1) {
			y |= i;
			if (y > 46340 || y * y > x)
				y ^= i;
		}
		return y;
	}
	
}
