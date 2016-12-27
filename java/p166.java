/* 
 * Solution to Project Euler problem 166
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p166 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p166().run());
	}
	
	
	/* 
	 * Give variables to the grid cells like this:
	 *   [ a b c d ]
	 *   [ e f g h ]
	 *   [ i j k l ]
	 *   [ m n o p ]
	 * There are 10 four-element lines in the grid. Assume each line has a sum of s.
	 * We write 9 equations to equate the first row's sum with each other line's sum:
	 *   a + b + c + d  =  e + f + g + h.  (1st row = 2nd row)
	 *   a + b + c + d  =  i + j + k + l.  (1st row = 3rd row)
	 *   a + b + c + d  =  m + n + o + p.  (1st row = 4th row)
	 *   a + b + c + d  =  a + e + i + m.  (1st row = 1st column)
	 *   a + b + c + d  =  b + f + j + n.  (1st row = 2nd column)
	 *   a + b + c + d  =  c + g + k + o.  (1st row = 3rd column)
	 *   a + b + c + d  =  d + h + l + p.  (1st row = 4th column)
	 *   a + b + c + d  =  a + f + k + p.  (1st row = diagonal)
	 *   a + b + c + d  =  d + g + j + m.  (1st row = antidiagonal)
	 * Actually only 8 of these equations are linearly independent.
	 * So with 16 variables and 8 equations, we have 8 free variables.
	 * Suppose we know the values of {a,b,c,d,e,g,i,k}. We perform some algebra:
	 *   m = b + c + d - e - i.  (based on 1st column)
	 *   o = a + b + d - g - k.  (based on 3rd column)
	 *   j = a + b + c - g - m.  (based on antidiagonal)
	 *   l = a + b + c + d - i - j - k.  (based on 3rd row)
	 * Now comes a difficult step. We need to solve a full system of linear equations to get the rest of the values.
	 * Here I state the result for f and show its correctness, but not explain how the solution was found:
	 *   - 1 (a + b + c + d)     - 1 (m + n + o + p)
	 *   + 2 (a + b + c + d)     + 2 (a + e + i + m)
	 *   + 1 (a + b + c + d)  =  + 1 (b + f + j + n)  .
	 *   + 1 (a + b + c + d)     + 1 (c + g + k + o)
	 *   + 1 (a + b + c + d)     + 1 (a + f + k + p)
	 *   - 1 (a + b + c + d)     - 1 (d + g + j + m)
	 * Adding up all terms on the left side and on the right side, and simplifying, we get:
	 *   3(a + b + c + d) = 3a + 2e + 2i + b + 2f + c + 2k - d.
	 *   f = b + c + 2d - e - i - k.
	 * The rest of the way is smooth sailing:
	 *   h = a + b + c + d - e - f - g.  (based on 2nd row)
	 *   n = a + c + d - f - j.  (based on 2nd column)
	 *   p = a + b + c - h - l.  (based on 4th column)
	 */
	public String run() {
		int count = 0;
		int[] num = new int[8];  // A counter in base 10, little-endian
		do {
			int a = num[0], b = num[1], c = num[2], d = num[3];
			int e = num[4], g = num[5], i = num[6], k = num[7];
			int m = b + c + d - e - i;          if (m < 0 || m > 9) continue;
			int o = a + b + d - g - k;          if (o < 0 || o > 9) continue;
			int j = a + b + c - g - m;          if (j < 0 || j > 9) continue;
			int l = a + b + c + d - i - j - k;  if (l < 0 || l > 9) continue;
			int f = b + c + d*2 - e - i - k;    if (f < 0 || f > 9) continue;
			int h = a + b + c + d - e - f - g;  if (h < 0 || h > 9) continue;
			int n = a + c + d - f - j;          if (n < 0 || n > 9) continue;
			int p = a + b + c - h - l;          if (p < 0 || p > 9) continue;
			count++;  // All tests passed
		} while (increment(num));
		return Integer.toString(count);
	}
	
	
	// Increments the given base-10 counter in little endian, e.g. {9, 9, 5, 1} -> {0, 0, 6, 1}.
	// Returns true if incremented, otherwise returns false if all elements are already 9.
	private static boolean increment(int[] num) {
		int i = 0;
		while (num[i] == 9) {
			num[i] = 0;
			i++;
			if (i == num.length)
				return false;
		}
		num[i]++;
		return true;
	}
	
}
