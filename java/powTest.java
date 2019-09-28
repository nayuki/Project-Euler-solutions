import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public final class LibraryTest {

  // When x < 0 will throw new Exception
  @Test
  public void powTestNegativeX() {
    final int negative_x = -2;
    final int y = 0;
    assertEquals(new IllegalArgumentException("Negative base not supported"), Library.pow(negative_x, y));
  }

  // When y < 0 will throw new Exception
  @Test
  public void powTestNegativeY() {
    final int x = 5;
    final int negative_y = -2;
    assertEquals(new IllegalArgumentException("Negative exponent"), Library.pow(x, negative_y));
  }

  // When x < 0, y < 0 but will throw new Exception for value of x before
  @Test
  public void powTestNegativeXY() {
    final int negative_x = -10;
    final int negative_y = -6;
    assertEquals(new IllegalArgumentException("Negative base not supported"), Library.pow(negative_x, negative_y));
  }

  // When x > 0 && y > 0 will return x^y
  @Test
  public void powTest1() {
    final int x = 2;
    final int y = 3;
    final int expected = 8;
    assertEquals(expected, Library.pow(x, y));
  }

  // When x > 0 && y = 0 will return 1
  @Test
  public void powTest2() {
    final int x = 100;
    final int y = 0;
    final int expected = 1;
    assertEquals(expected, Library.pow(x, y));
  }

  // When x = 0 && y >= 0 will return 0
  @Test
  public void powTest3() {
    final int x = 0;
    final int y = 1000;
    final int expected = 0;
    assertEquals(expected, Library.pow(x, y));
  }

  // When x^y > Integer.MAX_VALUE will throw new Exception
  @Test
  public void powTestInvalidValue() {
    final int x = 2;
    final int invalid_y = 32;
    assertEquals(new ArithmeticException("Overflow"), Library.pow(x, invalid_y));
  }
}
