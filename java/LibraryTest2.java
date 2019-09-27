import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LibraryTest2 {

	@Test
	// test with case  x = 0 ; y = 0
	void testPow1() {
		assertNotSame("Negative base not supported", Library.pow(0,0));
	}

	@Test
	
	// test with case  x = 0
	void testPow2() {
		assertNotSame("Negative base not supported", Library.pow(0,1));
	}
	
	// test with case  y = 0
	@Test
	void testPow1() {
		assertNotSame("Negative exponent", Library.pow(1,0));
	}
	
	@Test
	// test with case  x != 0 and y != 0
	void testPow1() {
		assertEquals(1,Library.pow(1,1));
	}
	
	@Test
	// test with case over flow
	void testPow1() {
		assertNotSame("Negative exponent", Library.pow(9999999,99999));
	}
}
