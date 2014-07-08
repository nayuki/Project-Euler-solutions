import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;


public final class LibraryTest {
	
	@Test
	public void testReverse() {
		assertEquals("", Library.reverse(""));
		assertEquals("a", Library.reverse("a"));
		assertEquals("ba", Library.reverse("ab"));
		assertEquals("001", Library.reverse("100"));
		assertEquals("a0a", Library.reverse("a0a"));
	}
	
	
	@Test
	public void testIsPalindromeString() {
		assertTrue(Library.isPalindrome(""));
		assertTrue(Library.isPalindrome("a"));
		assertTrue(Library.isPalindrome("aa"));
		assertTrue(Library.isPalindrome("aaa"));
		assertTrue(Library.isPalindrome("aaaa"));
		assertTrue(Library.isPalindrome("aba"));
		assertTrue(Library.isPalindrome("abba"));
		assertTrue(Library.isPalindrome("abbba"));
		assertTrue(Library.isPalindrome("acbca"));
		assertFalse(Library.isPalindrome("ab"));
		assertFalse(Library.isPalindrome("ba"));
		assertFalse(Library.isPalindrome("aaba"));
		assertFalse(Library.isPalindrome("abcd"));
	}
	
	
	@Test
	public void testIsPalindromeInt() {
		assertTrue(Library.isPalindrome(0));
		assertTrue(Library.isPalindrome(1));
		assertTrue(Library.isPalindrome(5));
		assertTrue(Library.isPalindrome(11));
		assertTrue(Library.isPalindrome(33));
		assertTrue(Library.isPalindrome(101));
		assertTrue(Library.isPalindrome(151));
		assertTrue(Library.isPalindrome(737));
		assertTrue(Library.isPalindrome(2222));
		assertTrue(Library.isPalindrome(5665));
		assertTrue(Library.isPalindrome(2147447412));
		assertFalse(Library.isPalindrome(12));
		assertFalse(Library.isPalindrome(43));
		assertFalse(Library.isPalindrome(220));
		assertFalse(Library.isPalindrome(1010));
		assertFalse(Library.isPalindrome(2147483647));
	}
	
	
	@Test
	public void testSqrtInt() {
		assertEquals(0, Library.sqrt(0));
		assertEquals(1, Library.sqrt(1));
		assertEquals(1, Library.sqrt(2));
		assertEquals(1, Library.sqrt(3));
		assertEquals(2, Library.sqrt(4));
		assertEquals(2, Library.sqrt(5));
		assertEquals(2, Library.sqrt(8));
		assertEquals(3, Library.sqrt(9));
		assertEquals(3, Library.sqrt(10));
		assertEquals(18, Library.sqrt(360));
		assertEquals(19, Library.sqrt(361));
		assertEquals(19, Library.sqrt(362));
		assertEquals(256, Library.sqrt(65536));
		assertEquals(32768, Library.sqrt(1073741824));
		assertEquals(46340, Library.sqrt(2147483647));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSqrtIntInvalid0() {
		Library.sqrt(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSqrtIntInvalid1() {
		Library.sqrt(-300000);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSqrtIntInvalid2() {
		Library.sqrt(Integer.MIN_VALUE);
	}
	
	
	@Test
	public void testSqrtLong() {
		assertEquals(0L, Library.sqrt(0L));
		assertEquals(1L, Library.sqrt(1L));
		assertEquals(1L, Library.sqrt(2L));
		assertEquals(1L, Library.sqrt(3L));
		assertEquals(2L, Library.sqrt(4L));
		assertEquals(2L, Library.sqrt(5L));
		assertEquals(2L, Library.sqrt(8L));
		assertEquals(3L, Library.sqrt(9L));
		assertEquals(3L, Library.sqrt(10L));
		assertEquals(18L, Library.sqrt(360L));
		assertEquals(19L, Library.sqrt(361L));
		assertEquals(19L, Library.sqrt(362L));
		assertEquals(256L, Library.sqrt(65536L));
		assertEquals(32768L, Library.sqrt(1073741824L));
		assertEquals(46340L, Library.sqrt(2147483648L));
		assertEquals(2645751L, Library.sqrt(7000000000000L));
		assertEquals(3037000499L, Library.sqrt(9223372036854775807L));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSqrtLongInvalid0() {
		Library.sqrt(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSqrtLongInvalid1() {
		Library.sqrt(-3000000000L);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSqrtLongInvalid2() {
		Library.sqrt(Long.MIN_VALUE);
	}
	
	
	@Test
	public void testIsSquare() {
		assertTrue(Library.isSquare(0));
		assertTrue(Library.isSquare(1));
		assertTrue(Library.isSquare(4));
		assertTrue(Library.isSquare(9));
		assertTrue(Library.isSquare(16));
		assertTrue(Library.isSquare(25));
		assertTrue(Library.isSquare(36));
		assertTrue(Library.isSquare(100));
		assertTrue(Library.isSquare(65536));
		assertTrue(Library.isSquare(2147302921));
		assertTrue(Library.isSquare(2147395600));
		assertFalse(Library.isSquare(Integer.MIN_VALUE));
		assertFalse(Library.isSquare(Integer.MIN_VALUE + 1));
		assertFalse(Library.isSquare(-8654038));
		assertFalse(Library.isSquare(-300));
		assertFalse(Library.isSquare(-4));
		assertFalse(Library.isSquare(-1));
		assertFalse(Library.isSquare(2));
		assertFalse(Library.isSquare(3));
		assertFalse(Library.isSquare(5));
		assertFalse(Library.isSquare(6));
		assertFalse(Library.isSquare(7));
		assertFalse(Library.isSquare(8));
		assertFalse(Library.isSquare(120));
		assertFalse(Library.isSquare(9999));
		assertFalse(Library.isSquare(Integer.MAX_VALUE - 1));
		assertFalse(Library.isSquare(Integer.MAX_VALUE));
	}
	
	
	@Test
	public void testPowMod() {
		assertEquals(0, Library.powMod(0, 0, 1));
		assertEquals(0, Library.powMod(1, 0, 1));
		assertEquals(0, Library.powMod(0, 1, 1));
		assertEquals(0, Library.powMod(1, 1, 1));
		assertEquals(0, Library.powMod(2, 3, 1));
		assertEquals(1, Library.powMod(0, 0, 2));
		assertEquals(1, Library.powMod(1, 0, 2));
		assertEquals(1, Library.powMod(2, 0, 2));
		assertEquals(0, Library.powMod(0, 1, 2));
		assertEquals(1, Library.powMod(1, 1, 2));
		assertEquals(0, Library.powMod(2, 1, 2));
		assertEquals(0, Library.powMod(0, 2, 2));
		assertEquals(1, Library.powMod(1, 2, 2));
		assertEquals(0, Library.powMod(2, 2, 2));
		assertEquals(1, Library.powMod(2, 2, 3));
		assertEquals(4, Library.powMod(4, 3, 5));
		assertEquals(3, Library.powMod(7, 7, 10));
		assertEquals(326216098, Library.powMod(78051657, 234602, 456087413));
		assertEquals(1488576545, Library.powMod(2147480000, 2147483645, 2147483647));
	}
	
	
	@Test
	public void testFactorial() {
		assertEquals(new BigInteger("1"), Library.factorial(0));
		assertEquals(new BigInteger("1"), Library.factorial(1));
		assertEquals(new BigInteger("2"), Library.factorial(2));
		assertEquals(new BigInteger("6"), Library.factorial(3));
		assertEquals(new BigInteger("24"), Library.factorial(4));
		assertEquals(new BigInteger("120"), Library.factorial(5));
		assertEquals(new BigInteger("720"), Library.factorial(6));
		assertEquals(new BigInteger("6227020800"), Library.factorial(13));
		assertEquals(new BigInteger("51090942171709440000"), Library.factorial(21));
		assertEquals(new BigInteger("265252859812191058636308480000000"), Library.factorial(30));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testFactorialInvalid0() {
		Library.factorial(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFactorialInvalid1() {
		Library.factorial(-563);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFactorialInvalid2() {
		Library.factorial(Integer.MIN_VALUE);
	}
	
	
	@Test
	public void testBinomial() {
		assertEquals(new BigInteger("1"), Library.binomial(0, 0));
		assertEquals(new BigInteger("1"), Library.binomial(1, 0));
		assertEquals(new BigInteger("1"), Library.binomial(1, 1));
		assertEquals(new BigInteger("1"), Library.binomial(2, 0));
		assertEquals(new BigInteger("2"), Library.binomial(2, 1));
		assertEquals(new BigInteger("1"), Library.binomial(2, 2));
		assertEquals(new BigInteger("1"), Library.binomial(3, 0));
		assertEquals(new BigInteger("3"), Library.binomial(3, 1));
		assertEquals(new BigInteger("3"), Library.binomial(3, 2));
		assertEquals(new BigInteger("1"), Library.binomial(3, 3));
		assertEquals(new BigInteger("35"), Library.binomial(7, 4));
		assertEquals(new BigInteger("120"), Library.binomial(10, 7));
		assertEquals(new BigInteger("21"), Library.binomial(21, 20));
		assertEquals(new BigInteger("88749815264600"), Library.binomial(50, 28));
	}
	
	
	@Test
	public void testGcd() {
		assertEquals(0, Library.gcd(0, 0));
		assertEquals(1, Library.gcd(0, 1));
		assertEquals(1, Library.gcd(1, 0));
		assertEquals(6, Library.gcd(0, 6));
		assertEquals(6, Library.gcd(6, 0));
		assertEquals(1, Library.gcd(1, 1));
		assertEquals(2, Library.gcd(2, 2));
		assertEquals(1, Library.gcd(2, 3));
		assertEquals(1, Library.gcd(10, 3));
		assertEquals(3, Library.gcd(9, 3));
		assertEquals(2, Library.gcd(6, 4));
		assertEquals(2, Library.gcd(18, 14));
		assertEquals(300, Library.gcd(44100, 48000));
		assertEquals(2147483647, Library.gcd(0, 2147483647));
		assertEquals(2147483647, Library.gcd(2147483647, 2147483647));
		assertEquals(1, Library.gcd(2147483646, 2147483647));
	}
	
	
	@Test
	public void testIsPrime() {
		assertFalse(Library.isPrime(0));
		assertFalse(Library.isPrime(1));
		assertTrue(Library.isPrime(2));
		assertTrue(Library.isPrime(3));
		assertFalse(Library.isPrime(4));
		assertTrue(Library.isPrime(5));
		assertFalse(Library.isPrime(6));
		assertTrue(Library.isPrime(7));
		assertFalse(Library.isPrime(8));
		assertFalse(Library.isPrime(9));
		assertFalse(Library.isPrime(10));
		assertTrue(Library.isPrime(11));
		assertFalse(Library.isPrime(12));
		assertTrue(Library.isPrime(13));
		assertFalse(Library.isPrime(14));
		assertFalse(Library.isPrime(15));
		assertFalse(Library.isPrime(16));
		assertTrue(Library.isPrime(17));
		assertFalse(Library.isPrime(18));
		assertTrue(Library.isPrime(19));
		assertFalse(Library.isPrime(20));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testIsPrimeInvalid0() {
		Library.isPrime(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIsPrimeInvalid1() {
		Library.isPrime(-3000);
	}
	
	
	@Test
	public void testListPrimality() {
		boolean[] isPrime = Library.listPrimality(1000);
		for (int i = 0; i < isPrime.length; i++)
			assertEquals(Library.isPrime(i), isPrime[i]);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPrimalityInvalid0() {
		Library.listPrimality(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPrimalityInvalid1() {
		Library.listPrimality(-3000);
	}
	
	
	@Test
	public void testListPrimes() {
		int limit = 1000;
		int[] primes = Library.listPrimes(limit);
		for (int i = 0; i < primes.length - 1; i++)
			assertTrue(primes[i] < primes[i + 1]);
		for (int i = 0; i <= limit; i++)
			assertEquals(Library.isPrime(i), Arrays.binarySearch(primes, i) >= 0);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPrimesInvalid0() {
		Library.listPrimes(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testListPrimesInvalid1() {
		Library.listPrimes(-3000);
	}
	
	
	@Test
	public void testTotient() {
		assertEquals( 1, Library.totient( 1));
		assertEquals( 1, Library.totient( 2));
		assertEquals( 2, Library.totient( 3));
		assertEquals( 2, Library.totient( 4));
		assertEquals( 4, Library.totient( 5));
		assertEquals( 2, Library.totient( 6));
		assertEquals( 6, Library.totient( 7));
		assertEquals( 4, Library.totient( 8));
		assertEquals( 6, Library.totient( 9));
		assertEquals( 4, Library.totient(10));
		assertEquals(10, Library.totient(11));
		assertEquals( 4, Library.totient(12));
		assertEquals(12, Library.totient(13));
		assertEquals( 6, Library.totient(14));
		assertEquals( 8, Library.totient(15));
		assertEquals( 8, Library.totient(16));
		assertEquals(16, Library.totient(17));
		assertEquals( 6, Library.totient(18));
		assertEquals(18, Library.totient(19));
		assertEquals( 8, Library.totient(20));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testTotientInvalid0() {
		Library.totient(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTotientInvalid1() {
		Library.totient(-3000);
	}
	
	
	@Test
	public void testListTotients() {
		int[] totients = Library.listTotients(1000);
		for (int i = 1; i < totients.length; i++)
			assertEquals(Library.totient(i), totients[i]);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testListTotientsInvalid0() {
		Library.listTotients(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testListTotientsInvalid1() {
		Library.listTotients(-3000);
	}
	
	
	@Test
	public void testNextPermutation() {
		int[] arr;
		
		assertFalse(Library.nextPermutation(new int[0]));
		
		arr = new int[]{0, 0, 1};
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{0, 1, 0}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 0, 0}, arr);
		assertFalse(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 0, 0}, arr);
		
		arr = new int[]{1, 2, 3, 5, 9};
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 2, 3, 9, 5}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 2, 5, 3, 9}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 2, 5, 9, 3}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 2, 9, 3, 5}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 2, 9, 5, 3}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 3, 2, 5, 9}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 3, 2, 9, 5}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 3, 5, 2, 9}, arr);
		assertTrue(Library.nextPermutation(arr));  assertArrayEquals(new int[]{1, 3, 5, 9, 2}, arr);
		for (int i = 0; i < 110; i++)
			assertTrue(Library.nextPermutation(arr));
		assertFalse(Library.nextPermutation(arr));  assertArrayEquals(new int[]{9, 5, 3, 2, 1}, arr);
	}
	
}
