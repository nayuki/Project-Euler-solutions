import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


public class p108 {
	
	public static void main(String[] args) {
		int n = 1;
		while (numberOfSolutions(n) <= 1000)
			n++;
		System.out.println(n);
	}
	
	
	private static int numberOfSolutions(int n) {
		BigFraction nrec = getReciprocal(n);
		int count = 0;
		for (int x = n + 1; x <= 2 * n; x++) {
			BigFraction y = nrec.subtract(getReciprocal(x));
			if (y.getNumerator().equals(BigInteger.ONE))
				count++;
		}
		return count;
	}
	
	
	private static Map<Integer,BigFraction> reciprocals = new HashMap<Integer,BigFraction>();
	
	private static BigFraction getReciprocal(int x) {
		if (reciprocals.containsKey(x))
			return reciprocals.get(x);
		else {
			BigFraction result = new BigFraction(BigInteger.ONE, BigInteger.valueOf(x));
			reciprocals.put(x, result);
			return result;
		}
	}

}
