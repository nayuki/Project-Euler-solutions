import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public final class LibraryTest {

  // When x < 0 will throw new Exception
  @Test
  public void primeTestNegativeX() {
    final int negative_x = -2;
    assertEquals(new IllegalArgumentException("Negative numberd"), Library.isPrime(negative_x));
  }

  // When x = 0 will return false
  @Test
  public void primeTest1() {
    final int x = 0;
    final boolean expected = false;
    assertEquals(expected, Library.isPrime(x));
  }

  // When x = 1 will return false
  @Test
  public void primeTest2() {
    final int x = 1;
    final boolean expected = false;
    assertEquals(expected, Library.isPrime(x));
  }

  // When x = 2 will return true
  @Test
  public void primeTest3() {
    final int x = 2;
    final boolean expected = true;
    assertEquals(expected, Library.isPrime(x));
  }

  // When x > 2 will return x is prime number or not
  @Test
  public void primeTest4() {
    final int x = 3;
    final boolean expected = true;
    assertEquals(expected, Library.isPrime(x));
  }

  // When x > 2 and x % 2 == 0 will return false
  @Test
  public void primeTest5() {
    final int x = 8;
    final boolean expected = false;
    assertEquals(expected, Library.isPrime(x));
  }

}