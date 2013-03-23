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
	
	
	private static int longestConsecutive(int a, int b, int c, int d) {
		Set<Integer> expressible = new HashSet<Integer>();
		
		// Try all possible orderings of operands and operators
		int[] interleaving = {0, 0, 0, 0, 1, 1, 1};  // 0 = operand, 1 = operator
		outer:
		do {
			// Try all possibilities for operands
			int[] operands = {a, b, c, d};
			do {
				// Try all possibilities for operators
				tryOperators:
				for (int i = 0; i < 64; i++) {
					Stack<Fraction> stack = new Stack<Fraction>();
					int j = 0;  // Operand index
					int k = 0;  // Operator index
					for (int op : interleaving) {
						if (op == 0) {
							stack.push(new Fraction(BigInteger.valueOf(operands[j]), BigInteger.ONE));
							j++;
						} else if (op == 1) {
							if (stack.size() < 2)
								continue outer;  // Malformed RPN expression; skip this interleaving
							Fraction right = stack.pop();
							Fraction left = stack.pop();
							switch ((i >>> (k * 2)) & 3) {
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
										continue tryOperators;  // Skip the result for this case
									stack.push(left.divide(right));
									break;
								default:
									throw new AssertionError();
							}
							k++;
						} else
							throw new AssertionError();
					}
					if (stack.size() != 1)
						throw new AssertionError();
					Fraction result = stack.pop();
					if (result.denominator.equals(BigInteger.ONE))
						expressible.add(result.numerator.intValue());
				}
			} while (Library.nextPermutation(operands));
		} while (Library.nextPermutation(interleaving));
		
		for (int i = 1; ; i++) {
			if (!expressible.contains(i))
				return i - 1;
		}
	}
	
}
