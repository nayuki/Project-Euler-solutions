/* 
 * Solution to Project Euler problem 94
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p094 {
	
	public static void main(String[] args) {
		long sum = 0;
		
		// Test triangles with side lengths {i, i, i+1}, excluding {1, 1, 2}
		for (long i = 2; i * 3 + 1 <= 1000000000; i++) {
			// The area of the triangle is (i + 1) sqrt(3i^2 - 2i - 1) / 4; check if it's an integer
			long a = 3*i*i - 2*i - 1;
			long b = Library.sqrt(a);
			if (b * b != a)
				continue;
			b *= i + 1;
			if (b % 4 != 0)
				continue;
			sum += i * 3 + 1;
		}
		
		// Test triangles with side lengths {i+1, i+1, i}
		for (long i = 1; i * 3 + 2 <= 1000000000; i++) {
			// The area of the triangle is i sqrt(3i^2 + 8i + 4) / 4; check if it's an integer
			long a = 3*i*i + 8*i + 4;
			long b = Library.sqrt(a);
			if (b * b != a)
				continue;
			b *= i;
			if (b % 4 != 0)
				continue;
			sum += i * 3 + 2;
		}
		
		System.out.println(sum);
	}
	
}
