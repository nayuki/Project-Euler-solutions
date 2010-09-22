public class p160 {
	
	public static void main(String[] args) {
		long x = 1;
		long twos = 0;
		for (int i = 1; i <= 1000000000; i++) {
			int j = i;
			for (; j % 2 == 0; twos++, j /= 2);
			for (; j % 5 == 0; twos--, j /= 5);
			
			x = x * j % 100000;
		}
		
		for (; twos > 0; x = x * 2 % 100000, twos--);
		
		System.out.println(x);
	}
	
}
