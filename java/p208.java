/* 
 * Solution to Project Euler problem 208
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public final class p208 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p208().run());
	}
	
	
	/* 
	 * Because the robot moves in 72-degree arcs, it can only face 5 possible directions.
	 * - Define the initial northward direction as the integer 0.
	 * - Let each anticlockwise (leftward) move add 1 to the direction, modulo 5.
	 * - Let each clockwise (rightward) move subtract 1 from the direction, modulo 5.
	 * 
	 * At each of the 5 possible facing directions, there are 2 possible moves.
	 * What is the (x, y) displacement of each of these 10 possible moves?
	 * 
	 * We can figure this out by drawing a circle with 5 points evenly spaced 72
	 * degrees apart. To align with the problem description, one of the points will
	 * be on the positive x axis. For our convenience, the circle shall have radius 4.
	 * Doing some moderate algebra and trigonometry, we get these point coordinates:
	 * - Point 0: (cos   0, sin   0)*4 = (+4, 0).
	 * - Point 1: (cos  72, sin  72)*4 = (+(sqrt(5)-1), +sqrt(10+2sqrt(5))).
	 * - Point 2: (cos 144, sin 144)*4 = (-(sqrt(5)+1), +sqrt(10-2sqrt(5))).
	 * - Point 3: (cos 216, sin 216)*4 = (-(sqrt(5)+1), -sqrt(10-2sqrt(5))).
	 * - Point 4: (cos 288, sin 288)*4 = (+(sqrt(5)-1), -sqrt(10+2sqrt(5))).
	 * (The inputs to cos and sin are given in degrees.)
	 * 
	 * This sequence of points is constructed so that the displacement vector from
	 * point k to point k+1 is equal to the displacement of making an anticlockwise
	 * move when facing direction k. For example, (point1 - point0) is the
	 * displacement of an anticlockwise move when facing north (dir=0).
	 * Hence, the displacement vectors of the 5 anticlockwise move are:
	 * - point1 - point0 = (-(5-sqrt(5)),  +sqrt(10+2sqrt(5))).
	 * - point2 - point1 = (-2sqrt(5)   , -2sqrt( 5-2sqrt(5))).
	 * - point3 - point2 = (0           , -2sqrt(10-2sqrt(5))).
	 * - point4 - point3 = (+2sqrt(5)   , -2sqrt( 5-2sqrt(5))).
	 * - point0 - point4 = (+(5-sqrt(5)),  +sqrt(10+2sqrt(5))).
	 * As for clockwise moves, simply take the displacements vectors above and negate the x values.
	 * The mapping of displacement vectors to direction states also needs to be negated modulo 5.
	 * 
	 * Altogether, we have this table of valid moves:
	 *    Direction | Move | x displacement |   y displacement
	 *   -----------+------+----------------+---------------------
	 *        0     | ACW  |  -(5-sqrt(5))  | +sqrt(10+2sqrt(5))
	 *        0     |  CW  |  +(5-sqrt(5))  | +sqrt(10+2sqrt(5))
	 *        1     | ACW  |    -2sqrt(5)   | -2sqrt(5-2sqrt(5))
	 *        1     |  CW  |  -(5-sqrt(5))  | +sqrt(10+2sqrt(5))
	 *        2     | ACW  |        0       | -2sqrt(10-2sqrt(5))
	 *        2     |  CW  |    -2sqrt(5)   | -2sqrt(5-2sqrt(5))
	 *        3     | ACW  |    +2sqrt(5)   | -2sqrt(5-2sqrt(5))
	 *        3     |  CW  |        0       | -2sqrt(10-2sqrt(5))
	 *        4     | ACW  |  +(5-sqrt(5))  | +sqrt(10+2sqrt(5))
	 *        4     |  CW  |    +2sqrt(5)   | -2sqrt(5-2sqrt(5))
	 * Note that -2sqrt(5-2sqrt(5)) = sqrt(10-2sqrt(5)) - sqrt(10+2sqrt(5)).
	 * 
	 * As the robot moves, it adds x components and y components to its displacement.
	 * - At any given time, the x coordinate equals a unique integer-weighted
	 *   sum of 2sqrt(5) and (sqrt(5)-5), namely i*2sqrt(5) + j*(sqrt(5)-5).
	 *   We can show that these two irrational components cannot "simplify"
	 *   with each other - i.e. when (i, j) != (0, 0), the sum must be non-zero.
	 * - At any given time, the y coordinate equals a unique integer-weighted
	 *   sum of sqrt(10-2sqrt(5)) and sqrt(10+2sqrt(5)). We will assume without proof that
	 *   these two components don't "interact" with each other or cancel out in any way.
	 */
	
	private static final int LIMIT = 70;
	
	public String run() {
		// Start with the initial state being reachable
		Map<State,BigInteger> reachable = new HashMap<>();
		reachable.put(new State(0, 0, 0, 0, 0), BigInteger.ONE);
		
		// In each iteration, look at the set of reachable states and apply ACW and CW
		// moves on each state. Count the number of ways the new states can be reached.
		for (int i = 0; i < LIMIT; i++) {
			Map<State,BigInteger> newReachable = new HashMap<>();
			for (Map.Entry<State,BigInteger> entry : reachable.entrySet()) {
				State state = entry.getKey();
				BigInteger ways = entry.getValue();
				
				State acw = state.move(true);
				BigInteger temp = newReachable.containsKey(acw) ? newReachable.get(acw) : BigInteger.ZERO;
				newReachable.put(acw, temp.add(ways));
				
				State cw = state.move(false);
				temp = newReachable.containsKey(cw) ? newReachable.get(cw) : BigInteger.ZERO;
				newReachable.put(cw, temp.add(ways));
			}
			reachable = newReachable;
		}
		
		// Add up all the ways to reach the initial position using
		// exactly LIMIT moves, finally facing any possible direction
		BigInteger sum = BigInteger.ZERO;
		for (int dir = 0; dir < 5; dir++) {
			State st = new State(dir, 0, 0, 0, 0);
			if (reachable.containsKey(st))
				sum = sum.add(reachable.get(st));
		}
		return sum.toString();
	}
	
	
	
	// Represents a facing-direction and a position on the infinite plane.
	// The position is a weighted sum of irrational x components and y components.
	private static final class State {
		
		public final int direction;  // In the range [0, 5).
		public final int xComponent0;  // Any integer. For 2sqrt(5).
		public final int xComponent1;  // Any integer. For sqrt(5)-5.
		public final int yComponent0;  // Any integer. For sqrt(10-2sqrt(5)).
		public final int yComponent1;  // Any integer. For sqrt(10+2sqrt(5)).
		
		
		public State(int dir, int xComp0, int xComp1, int yComp0, int yComp1) {
			if (dir < 0 || dir >= 5)
				throw new IllegalArgumentException();
			direction = dir;
			xComponent0 = xComp0;
			xComponent1 = xComp1;
			yComponent0 = yComp0;
			yComponent1 = yComp1;
		}
		
		
		public State move(boolean anticlockwise) {
			int sign = anticlockwise ? +1 : -1;
			int[] entry = ANTICLOCKWISE_MOVES[(direction * sign + 5) % 5];
			return new State(
				(direction + sign + 5) % 5,
				xComponent0 + entry[0] * sign,
				xComponent1 + entry[1] * sign,
				yComponent0 + entry[2],
				yComponent1 + entry[3]);
		}
		
		
		public boolean equals(Object obj) {
			if (!(obj instanceof State))
				return false;
			State other = (State)obj;
			return
				direction   == other.direction   &&
				xComponent0 == other.xComponent0 &&
				xComponent1 == other.xComponent1 &&
				yComponent0 == other.yComponent0 &&
				yComponent1 == other.yComponent1;
		}
		
		
		public int hashCode() {
			return Objects.hash(direction,
				xComponent0, xComponent1,
				yComponent0, yComponent1);
		}
		
		
		private static final int[][] ANTICLOCKWISE_MOVES = {
			{ 0, -1,  0, +1},
			{-1,  0, +1, -1},
			{ 0,  0, -2,  0},
			{+1,  0, +1, -1},
			{ 0, +1,  0, +1},
		};
		
	}
	
}
