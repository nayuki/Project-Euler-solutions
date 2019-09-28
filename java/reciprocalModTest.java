import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public final class LibraryTest {

  // When x < 0 will throw new Exception
  @Test
  public void XLessThan0Test() {
    final int negative_x = -1;
    final int m = 1;
    assertEquals(new IllegalArgumentException(), Library.reciprocalMod(negative_x, m));
  }

  // When x > m will throw new Exception
  @Test
  public void XGreaterThanMTest() {
    final int x = 5;
    final int m = 4;
    assertEquals(new IllegalArgumentException(), Library.reciprocalMod(x, m));
  }

  // When x < 0, m < x throw new Exception
  @Test
  public void NegativeXMTest() {
    final int negative_x = -10;
    final int negative_m = -11;
    assertEquals(new IllegalArgumentException(), Library.reciprocalMod(negative_x, negative_m));
  }

  // When x = 0 && m = 1 will return 0
  @Test
  public void X0_M1_Test() {
    final int x = 0;
    final int m = 1;
    final int expected = 0;
    assertEquals(expected, Library.reciprocalMod(x, m));
  }

  // When x = 0 && m > 1 will throw new Exception
  @Test
  public void X0_MGreaterThan1_Test() {
    final int x = 0;
    final int m = 3;
    assertEquals(new IllegalArgumentException("Reciprocal does not exist"), Library.reciprocalMod(x, m));
  }

  // When x = 1 && m > x will return 1
  @Test
  public void X1_MGreaterThanX_Test() {
    final int x = 1;
    final int m = 1000;
    final int expected = 1;
    assertEquals(expected, Library.reciprocalMod(x, m));
  }

  // When x > 1 && m > x will return value
  @Test
  public void XMValidTest1() {
    final int x = 3;
    final int m = 5;
    final int expected = 2;
    assertEquals(expected, Library.reciprocalMod(x, m));
  }
  
  // When x > 1 && m > x can throw exception
  @Test
  public void XMValidTest2() {
    final int x = 3;
    final int m = 6;
    assertEquals(new IllegalArgumentException("Reciprocal does not exist"), Library.reciprocalMod(x, m));
  }
}
