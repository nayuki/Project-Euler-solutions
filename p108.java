public class p108 {
	
	public static void main(String[] args) {
		int n = 1;
		while (numberOfSolutions(n) <= 1000)
			n++;
		System.out.println(n);
	}
	
	
	private static int numberOfSolutions(int n) {
		int count = 0;
		for (int x = n + 1; x <= 2 * n; x++) {
			if ((long)x * n % (x - n) == 0)
				count++;
		}
		return count;
	}
	
}
