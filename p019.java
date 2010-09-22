public class p019 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int y = 1901; y < 2001; y++) {
			for (int m = 1; m <= 12; m++) {
				if (dayOfWeek(y, m, 1) == 0)
					count++;
			}
		}
		System.out.println(count);
	}
	
	
	private static int dayOfWeek(int year, int month, int day) {
		year = mod(year, 400);
		month = (int)mod((long)month - 3, 4800);
		year += month / 12;
		month %= 12;
		day = mod(day, 7);
		return (year + year/4 - year/100 + year/400 + (13*month+2)/5 + day + 2) % 7;
	}
	
	
	private static int mod(int x, int y) {
		x %= y;
		if (y > 0 && x < 0 || y < 0 && x > 0)
			x += y;
		return x;
	}
	
	
	private static long mod(long x, long y) {
		x %= y;
		if (y > 0 && x < 0 || y < 0 && x > 0)
			x += y;
		return x;
	}
	
}
