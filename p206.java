import java.math.BigInteger;

public class p206 {
	
	public static void main(String[] args) {
		long i = 1000000000;
		while (!isConcealedSquare(i))
			i++;
		System.out.println(i);
	}
	
	
	private static boolean isConcealedSquare(long n) {
		return BigInteger.valueOf(n).pow(2).toString().matches("1.2.3.4.5.6.7.8.9.0");
	}

}
