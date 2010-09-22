import java.math.BigInteger;


public final class BigFraction implements Comparable<BigFraction> {
	
	public static final BigFraction ZERO = new BigFraction(0, 1);
	
	public static final BigFraction ONE = new BigFraction(1, 1);
	
	
	
	private BigInteger numerator;
	
	private BigInteger denominator;
	
	
	
	public BigFraction(long value) {
		numerator = BigInteger.valueOf(value);
		denominator = BigInteger.ONE;
	}
	
	
	public BigFraction(long num, long den) {
		this(BigInteger.valueOf(num), BigInteger.valueOf(den));
	}
	
	
	public BigFraction(BigInteger num, BigInteger den) {
		if (num == null || den == null)
			throw new NullPointerException();
		if (den.equals(BigInteger.ZERO))
			throw new IllegalArgumentException("Denominator is zero");
		
		// Make denominator positive
		if (den.compareTo(BigInteger.ZERO) < 0) {
			num = num.negate();
			den = den.negate();
		}
		
		// Reduce to lowest terms
		BigInteger gcd = num.gcd(den);
		if (!gcd.equals(BigInteger.ONE)) {
			num = num.divide(gcd);
			den = den.divide(gcd);
		}
		
		// Now each number is in its one and only canonical representation
		numerator = num;
		denominator = den;
	}
	
	
	
	public BigInteger getNumerator() {
		return numerator;
	}
	
	
	public BigInteger getDenominator() {
		return denominator;
	}
	
	
	
	public BigFraction add(BigFraction other) {
		BigInteger num = numerator.multiply(other.denominator).add(other.numerator.multiply(denominator));
		BigInteger den = denominator.multiply(other.denominator);
		return new BigFraction(num, den);
	}
	
	
	public BigFraction subtract(BigFraction other) {
		BigInteger num = numerator.multiply(other.denominator).subtract(other.numerator.multiply(denominator));
		BigInteger den = denominator.multiply(other.denominator);
		return new BigFraction(num, den);
	}
	
	
	public BigFraction multiply(BigFraction other) {
		BigInteger num = numerator.multiply(other.numerator);
		BigInteger den = denominator.multiply(other.denominator);
		return new BigFraction(num, den);
	}
	
	
	public BigFraction divide(BigFraction other) {
		if (other.numerator.equals(BigInteger.ZERO))
			throw new ArithmeticException("Division by zero");
		BigInteger num = numerator.multiply(other.denominator);
		BigInteger den = denominator.multiply(other.numerator);
		return new BigFraction(num, den);
	}
	
	
	public BigFraction negate() {
		return new BigFraction(numerator.negate(), denominator);
	}
	
	
	public BigFraction reciprocal() {
		if (numerator.equals(BigInteger.ZERO))
			throw new ArithmeticException("Division by zero");
		return new BigFraction(denominator, numerator);
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BigFraction))
			return false;
		else {
			BigFraction other = (BigFraction)obj;
			return numerator.equals(other.numerator) && denominator.equals(other.denominator);
		}
	}
	
	
	public int compareTo(BigFraction other) {
		return numerator.multiply(other.denominator).compareTo(other.numerator.multiply(denominator));
	}
	
	
	@Override
	public int hashCode() {
		return numerator.hashCode() ^ denominator.hashCode();
	}
	
	
	@Override
	public String toString() {
		return String.format("%d/%d", numerator, denominator);
	}
	
}