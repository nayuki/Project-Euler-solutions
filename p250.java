public class p250 {
	
	public static void main(String[] args) {
		long[] numSubsetsRemainder = new long[250];
		numSubsetsRemainder[0] = 1;
		
		for (int i = 1; i <= 250250; i++) {
			int temp = powMod(i, i, 250);
			long[] newArray = numSubsetsRemainder.clone();
			for (int j = 0; j < 250; j++)
				newArray[(j + temp) % 250] = (newArray[(j + temp) % 250] + numSubsetsRemainder[j]) % 10000000000000000L;
			numSubsetsRemainder = newArray;
		}
		
		System.out.println((numSubsetsRemainder[0] - 1 + 10000000000000000L) % 10000000000000000L);
	}
	
	
	
	private static int powMod(int x, int y, int m) {
		if (y < 0)
			throw new IllegalArgumentException();
		int z = 1;
		for (; y != 0; y >>>= 1, x = (int)((long)x * x % m)) {
			if ((y & 1) != 0)
				z = (int)((long)z * x % m);
		}
		return z;
	}
	
}
