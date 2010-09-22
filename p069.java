public class p069 {
	
	public static void main(String[] args) {
		double maxVal = -1;
		int maxNum = -1;
		for (int i = 1; i <= 1000000; i++) {
			double val = (double)i / totient(i);
			if (maxNum == -1 || val > maxVal) {
				maxVal = val;
				maxNum = i;
			}
		}
		System.out.println(maxNum);
	}
	
	
	private static int totient(int x) {
		if (x <= 0)
			throw new IllegalArgumentException("Totient of non-positive integer");
		int p = 1;
		for (int i = 2, end = sqrt(x); i <= end; i++) {  // Trial division
			if (x % i == 0) {  // Found a factor
				p *= i - 1;
				x /= i;
				while (x % i == 0) {
					p *= i;
					x /= i;
				}
				end = sqrt(x);
			}
		}
		if (x != 1)
			p *= x - 1;
		return p;
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
