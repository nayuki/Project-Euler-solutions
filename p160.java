public class p160 {
	
	public static void main(String[] args) {
		System.out.println(factorialLast5Digits(1, 1000000000000L));
	}
	
	
	private static int factorialLast5Digits(long start, long end) {
		long x = 1;
		long twos = 0;
		long i = start;
		
		for (; i <= end && i % 10 != 0; i++) {  // Iterate up to a multiple of 10
			long j = i;
			for (; j % 2 == 0; twos++, j /= 2);
			for (; j % 5 == 0; twos--, j /= 5);
			x = x * j % 100000;
		}
		for (; i + 9 <= end; i += 10) {  // Iterate by tens
			long j;
			// 0
			j = i;
			do {j >>>= 1; twos++;} while ((j & 1) == 0);
			do {j /= 5; twos--;} while (j % 5 == 0);
			x = x * j % 100000;
			
			// 1
			j = i + 1;
			x = x * j % 100000;
			
			// 2
			j = i + 2;
			do {j >>>= 1; twos++;} while ((j & 1) == 0);
			x = x * j % 100000;
			
			// 3
			j = i + 3;
			x = x * j % 100000;
			
			// 4
			j = i + 4;
			do {j >>>= 1; twos++;} while ((j & 1) == 0);
			x = x * j % 100000;
			
			// 5
			j = i + 5;
			do {j /= 5; twos--;} while (j % 5 == 0);
			x = x * j % 100000;
			
			// 6
			j = i + 6;
			do {j >>>= 1; twos++;} while ((j & 1) == 0);
			x = x * j % 100000;
			
			// 7
			j = i + 7;
			x = x * j % 100000;
			
			// 8
			j = i + 8;
			do {j >>>= 1; twos++;} while ((j & 1) == 0);
			x = x * j % 100000;
			
			// 9
			j = i + 9;
			x = x * j % 100000;
		}
		for (; i <= end; i++) {  // Iterate up to end
			long j = i;
			for (; j % 2 == 0; twos++, j /= 2);
			for (; j % 5 == 0; twos--, j /= 5);
			x = x * j % 100000;
		}
		
		return (int)(x * powMod(2, twos, 100000) % 100000);
	}
	
	
	private static long powMod(long x, long y, long m) {
		if (y < 0)
			throw new IllegalArgumentException();
		long z = 1;
		for (; y != 0; y >>>= 1, x = x * x % m) {
			if ((y & 1) != 0)
				z = z * x % m;
		}
		return z;
	}
	
}
