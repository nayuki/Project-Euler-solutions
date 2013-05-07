import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	
	
	@Test
	public void testListPrimality() {
		boolean[] isPrime = Library.listPrimality(1000);
		for (int i = 0; i < isPrime.length; i++)
			assertEquals(Library.isPrime(i), isPrime[i]);
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
	
	
	@Test
	public void testListTotients() {
		int[] totients = Library.listTotients(1000);
		for (int i = 1; i < totients.length; i++)
			assertEquals(Library.totient(i), totients[i]);
	}
	
}
