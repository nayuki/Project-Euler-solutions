public class p027 {
	
	public static void main(String[] args) {
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
		System.out.println(bestA * bestB);
	}
	
	
	private static int numberOfConsecutivePrimesGenerated(int a, int b) {
		int i = 0;
		while (isPrime(i * i + i * a + b))
			i++;
		return i;
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
