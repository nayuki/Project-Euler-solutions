/* 
 * Solution to Project Euler problem 93
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public final class p093 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p093().run());
	}
	
	
	public String run() {
		int longest = 0;
		int abcd = -1;
		for (int a = 1; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {
				for (int c = b + 1; c <= 9; c++) {
					for (int d = c + 1; d <= 9; d++) {
						int consec = longestConsecutive(a, b, c, d);
						if (consec > longest) {
							longest = consec;
							abcd = a * 1000 + b * 100 + c * 10 + d;
						}
					}
				}
			}
		}
		return Integer.toString(abcd);
	}
	
	
	// Assumes 1 <= a < b < c < d <= 9
	private static int longestConsecutive(int a, int b, int c, int d) {
		Set<Integer> expressible = new HashSet<Integer>();
		
		// Try all possible orderings of operands and operators
		int[] ops = {0, 0, 0, a, b, c, d};  // 0 = operator slot, 1 to 9 = literal operand
		outer:
		do {
			// Try all possibilities for the 3 operators
			inner:
			for (int i = 0; i < 64; i++) {
				Stack<Fraction> stack = new Stack<Fraction>();
				int j = 0;  // Operator index
				for (int op : ops) {
					if (1 <= op && op <= 9) {  // Operand
						stack.push(new Fraction(BigInteger.valueOf(op), BigInteger.ONE));
					} else if (op == 0) {  // Operator
						if (stack.size() < 2)
							continue outer;  // Stack underflow; skip this ordering
						Fraction right = stack.pop();
						Fraction left = stack.pop();
						switch ((i >>> (j * 2)) & 3) {
							case 0:
								stack.push(left.add(right));
								break;
							case 1:
								stack.push(left.subtract(right));
								break;
							case 2:
								stack.push(left.multiply(right));
								break;
							case 3:
								if (right.numerator.signum() == 0)
									continue inner;  // Division by zero; skip the result for this case
								stack.push(left.divide(right));
								break;
							default:
								throw new AssertionError();
						}
						j++;  // Consume an operator
					} else
						throw new AssertionError();
				}
				if (stack.size() != 1)
					throw new AssertionError();
				Fraction result = stack.pop();
				if (result.denominator.equals(BigInteger.ONE))
					expressible.add(result.numerator.intValue());
			}
		} while (Library.nextPermutation(ops));
		
		// Find largest set of consecutive expressible integers starting from 1
		for (int i = 0; ; i++) {
			if (!expressible.contains(i + 1))
				return i;
		}
	}
	
}
