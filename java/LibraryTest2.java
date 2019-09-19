import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LibraryTest2 {

	@Test
	void testPow() {
		assertNotSame("Negative base not supported", Library.pow(0,0));
		assertNotSame("Negative base not supported", Library.pow(0,1));
		assertNotSame("Negative exponent", Library.pow(1,0));
		assertEquals(1,Library.pow(1,1));
		assertNotSame("Negative exponent", Library.pow(9999999,99999));
	}

}
