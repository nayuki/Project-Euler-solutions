/* 
 * Solution to Project Euler problem 45
 * By Nayuki Minase
 */


public class p045 {
	
	public static void main(String[] args) {
		int i = 286;
		int j = 166;
		int k = 144;
		while (true) {
			long triangle = (long)i * (i + 1) / 2;
			long pentagon = (long)j * (j * 3 - 1) / 2;
			long hexagon  = (long)k * (k * 2 - 1);
			long min = Math.min(Math.min(triangle, pentagon), hexagon);
			if (min == triangle && min == pentagon && min == hexagon) {
				System.out.println(min);
				break;
			}
			if (min == triangle) i++;
			if (min == pentagon) j++;
			if (min == hexagon ) k++;
		}
	}
	
}
