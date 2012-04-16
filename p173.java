/* 
 * Solution to Project Euler problem 173
 * By Nayuki Minase
 */


public class p173 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int a = 3; a <= 1000000; a ++) {
			for (int b = a - 2; b >= 1; b -= 2) {
				long tiles = (long)a * a - (long)b * b;
				if (tiles > 1000000)
					break;
				count++;
			}
		}
		System.out.println(count);
	}
	
}
