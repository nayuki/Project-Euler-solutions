/* 
 * Solution to Project Euler problem 51
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;


public final class p051 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p051().run());
	}
	
	
	public String run() {
		boolean[] isPrime = Library.listPrimality(1000000);
		for (int i = 0; i < isPrime.length; i++) {
			if (isPrime[i]) {
				int[] n = toDigits(i);
				for (int mask = 0; mask < (1 << n.length); mask++) {
					int[] digits = doMask(n, mask);
					int count = 0;
					for (int j = 0; j < 10; j++) {
						if (digits[0] != 0 && isPrime[toNumber(digits)])
							count++;
						digits = addMask(digits, mask);
					}
					
					if (count == 8) {
						digits = doMask(n, mask);
						for (int j = 0; j < 10; j++) {
							if (digits[0] != 0 && isPrime[toNumber(digits)])
								return Integer.toString(toNumber(digits));
							digits = addMask(digits, mask);
						}
					}
				}
			}
		}
		throw new RuntimeException("Not found");
	}
	
	
	private static int[] toDigits(int n) {
		int[] temp = new int[10];
		int len = 0;
		do {
			temp[temp.length - 1 - len] = n % 10;
			n /= 10;
			len++;
		} while (n != 0);
		return Arrays.copyOfRange(temp, temp.length - len, temp.length);
	}
	
	
	private static int[] doMask(int[] digits, int mask) {
		int[] result = new int[digits.length];
		for (int i = 0; i < digits.length; i++)
			result[i] = digits[i] * (~mask >>> i & 1);
		return result;
	}
	
	
	private static int[] addMask(int[] digits, int mask) {
		int[] result = new int[digits.length];
		for (int i = 0; i < digits.length; i++)
			result[i] = digits[i] + (mask >>> i & 1);
		return result;
	}
	
	
	private static int toNumber(int[] digits) {
		int result = 0;
		for (int x : digits)
			result = result * 10 + x;
		return result;
	}
	
}
