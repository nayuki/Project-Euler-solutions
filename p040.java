/* 
 * Solution to Project Euler problem 40
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p040 {
	
	public static void main(String[] args) {
		System.out.println(new p040().run());
	}
	
	
	public String run() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 1000000; i++)
			sb.append(i);
		
		return Integer.toString(
			  getDigit(sb, 1)
			* getDigit(sb, 10)
			* getDigit(sb, 100)
			* getDigit(sb, 1000)
			* getDigit(sb, 10000)
			* getDigit(sb, 100000)
			* getDigit(sb, 1000000)
		);
	}
	
	
	private static int getDigit(StringBuilder sb, int i) {
		char c = sb.charAt(i - 1);
		if (c >= '0' && c <= '9')
			return c - '0';
		else
			throw new IllegalArgumentException();
	}
	
}
