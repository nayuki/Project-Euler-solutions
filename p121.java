/* 
 * Solution to Project Euler problem 121
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;


public final class p121 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p121().run());
	}
	
	
	public String run() {
		Fraction prob = winProbability(15, 0, 0);
		return prob.denominator.divide(prob.numerator).toString();
	}
	
	
	private Fraction winProbability(int totalTurns, int currentTurn, int blueObtained) {
		if (currentTurn == totalTurns)
			return fraction(blueObtained * 2 > totalTurns ? 1 : 0, 1);
		else {
			Fraction probRed  = fraction(currentTurn + 1, currentTurn + 2);
			Fraction probBlue = fraction(1              , currentTurn + 2);
			Fraction winProbIfRed  = winProbability(totalTurns, currentTurn + 1, blueObtained + 0);
			Fraction winProbIfBlue = winProbability(totalTurns, currentTurn + 1, blueObtained + 1);
			return probRed.multiply(winProbIfRed).add(probBlue.multiply(winProbIfBlue));
		}
	}
	
	
	private static Fraction fraction(int n, int d) {
		return new Fraction(BigInteger.valueOf(n), BigInteger.valueOf(d));
	}
	
}
