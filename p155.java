/* 
 * Solution to Project Euler problem 155
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;


public final class p155 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p155().run());
	}
	
	
	// Requires about 5 GiB of memory
	private static final int SIZE = 18;
	
	public String run() {
		@SuppressWarnings("unchecked")
		Set<Fraction>[] possible = new Set[SIZE + 1];
		Set<Fraction> all = new HashSet<Fraction>();
		possible[0] = new HashSet<Fraction>();
		possible[1] = new HashSet<Fraction>();
		possible[1].add(new Fraction(BigInteger.valueOf(60), BigInteger.ONE));
		all.addAll(possible[1]);
		
		for (int i = 2; i <= SIZE; i++) {
			Set<Fraction> poss = new HashSet<Fraction>();
			for (int j = 1; j <= i - j; j++) {
				for (Fraction a : possible[j]) {
					for (Fraction b : possible[i - j]) {
						poss.add(a.add(b));
						poss.add(a.multiply(b).divide(a.add(b)));
					}
				}
			}
			possible[i] = poss;
			all.addAll(poss);
		}
		return Integer.toString(all.size());
	}
	
}
