public class p179 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int i = 2; i < 10000000; i++) {
			if (countDivisors(i) == countDivisors(i + 1))
				count++;
		}
		System.out.println(count);
	}
	
	
	private static int countDivisors(int n) {
		int end = Library.sqrt(n);
		int count = 0;
		for (int i = 1; i <= end; i++) {
			if (n % i == 0)
				count += 2;
		}
		if (end * end == n)  // Perfect square
			count--;
		return count;
	}
	
}
