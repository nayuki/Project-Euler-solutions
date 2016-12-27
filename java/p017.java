/* 
 * Solution to Project Euler problem 17
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p017 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p017().run());
	}
	
	
	/* 
	 * - For the numbers 0 to 19, we write the single word:
	 *   {zero, one, two, three, four, five, six, seven, eight, nine,
	 *   ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen}.
	 * - For the numbers 20 to 99, we write the word for the tens place:
	 *   {twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety}.
	 *   Subsequently if the last digit is not 0, then we write the word for the ones place (one to nine).
	 * - For the numbers 100 to 999, we write the ones word for the hundreds place followed by "hundred":
	 *   {one hundred, two hundred, three hundred, ..., eight hundred, nine hundred}.
	 *   Subsequently if the last two digits are not 00, then we write the word "and"
	 *   followed by the phrase for the last two digits (from 01 to 99).
	 * - For the numbers 1000 to 999999, we write the word for the three digits starting at the
	 *   thousands place and going leftward, followed by "thousand". Subsequently if the last three
	 *   digits are not 000, then we write the phrase for the last three digits (from 001 to 999).
	 */
	public String run() {
		int sum = 0;
		for (int i = 1; i <= 1000; i++)
			sum += toEnglish(i).length();
		return Integer.toString(sum);
	}
	
	
	private static String toEnglish(int n) {
		if (0 <= n && n < 20)
			return ONES[n];
		else if (20 <= n && n < 100)
			return TENS[n / 10] + (n % 10 != 0 ? ONES[n % 10] : "");
		else if (100 <= n && n < 1000)
			return ONES[n / 100] + "hundred" + (n % 100 != 0 ? "and" + toEnglish(n % 100) : "");
		else if (1000 <= n && n < 1000000)
			return toEnglish(n / 1000) + "thousand" + (n % 1000 != 0 ? toEnglish(n % 1000) : "");
		else
			throw new IllegalArgumentException();
	}
	
	
	private static String[] ONES = {
		"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
		"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	
	private static String[] TENS = {
		"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	
}
