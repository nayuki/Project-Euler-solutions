import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public final class LibraryTest {

  //x < 0
  @Test
  public void powTestNegativeX() {
    final int negative_x = -5;
    final int y = 8;
    assertEquals(new IllegalArgumentException("Negative base not supported"), Library.pow(negative_x, y));
  }

  // y < 0
  @Test
  public void powTestNegativeY() {
    final int x = 9;
    final int negative_y = -102;
    assertEquals(new IllegalArgumentException("Negative exponent"), Library.pow(x, negative_y));
  }

  // x < 0, y < 0
  @Test
  public void powTestNegativeXY() {
    final int negative_x = -52;
    final int negative_y = -36;
    assertEquals(new IllegalArgumentException("Negative base not supported"), Library.pow(negative_x, negative_y));
  }

  //x > 0 && y > 0
  @Test
  public void powTest1() {
    final int x = 21;
    final int y = 23;
    final int expected = 528;
    assertEquals(expected, Library.pow(x, y));
  }

  // x > 0 && y = 0
  @Test
  public void powTest2() {
    final int x = 50;
    final int y = 0;
    final int expected = 1;
    assertEquals(expected, Library.pow(x, y));
  }

  //  x = 0 && y >= 0
  @Test
  public void powTest3() {
    final int x = 0;
    final int y = 1000;
    final int expected = 0;
    assertEquals(expected, Library.pow(x, y));
  }

  // x^y > Integer.MAX_VALUE
  @Test
  public void powTestInvalidValue() {
    final int x = 2;
    final int invalid_y = 32;
    assertEquals(new ArithmeticException("Overflow"), Library.pow(x, invalid_y));
  }
}