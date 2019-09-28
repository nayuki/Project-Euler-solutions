import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public final class LibraryTest {


    // When x < 0 will throw new Exception
    @Test
    public void powModTestNegativeX() {
        final int x = -1;
        final int y = 0;
        final int m = 2;
        assertEquals(new IllegalArgumentException("Negative base not supported"), Library.powMod(x, y, m));
    }

    // When y < 0 will throw new Exception
    @Test
    public void powModTestNegativeY() {
        final int y = -1;
        final int x = 0;
        final int m = 2;
        assertEquals(new IllegalArgumentException("Modular reciprocal not supported"), Library.powMod(x, y, m));
    }

    // When m <= 0 will throw new Exception
    @Test
    public void powModTestNegativeM() {
        final int x = 1;
        final int y = 1;
        final int m = 0;
        assertEquals(new IllegalArgumentException("Modulus must be positive"), Library.powMod(x, y, m));
    }

    // When m == 1 will return 0
    @Test
    public void powModTestNegativeM2() {
        final int x = 1;
        final int y = 1;
        final int m = 1;
        assertEquals(0, Library.powMod(x, y, m));
    }

    // When x >= 0 && y >= 0 && m > 1 will return x^y mod m
    @Test
    public void powModTest_TC1() {
        final int x = 2;
        final int y = 3;
        final int m = 3;
        assertEquals(2, Library.powMod(x, y, m));
    }

    // When x >= 0 && y >= 0 && m > 1 will return x^y mod m
    @Test
    public void powModTest_TC2() {
        final int x = 74;
        final int y = 23;
        final int m = 28;
        assertEquals(16, Library.powMod(x, y, m));
    }

    // When x >= 0 && y >= 0 && m > 1 will return x^y mod m
    @Test
    public void powModTest_TC3() {
        final int x = 2638;
        final int y = 6987;
        final int m = 23;
        assertEquals(3, Library.powMod(x, y, m));
    }

    // When x >= 0 && y >= 0 && m > 1 will return x^y mod m
    @Test
    public void powModTest_TC4() {
        final int x = 2638236;
        final int y = 6987287;
        final int m = 23369;
        assertEquals(18498, Library.powMod(x, y, m));
    }


} 