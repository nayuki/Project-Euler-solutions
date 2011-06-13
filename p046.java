public class p046 {
	
	private static boolean[] isPrime = Library.listPrimality(10000);
	
	
	public static void main(String[] args) {
		outer:
		for (int i = 3; i < isPrime.length; i += 2) {
			if (!isPrime[i]) {
				for (int j = 1; 2 * j * j <= i; j++) {
					if (isPrime[i - 2 * j * j])
						continue outer;
				}
				System.out.println(i);
				break;
			}
		}
	}
	
}
