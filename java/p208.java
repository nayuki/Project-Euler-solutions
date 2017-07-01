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
	 * - Let the initial northward direction be defined as the integer 0.
	 * - Let each anticlockwise (leftward) move add 1 from the direction, modulo 5.
	 * - Let each clockwise (rightward) move subtract 1 to the direction, modulo 5.
	 * 
	 * There are 5 possible direction states, and 2 possible moves from each state.
	 * What is the (x, y) displacement of each of these 10 possible moves?
	 * 
	 * We can figure this out by drawing a unit circle with 5 points evenly
	 * spaced 72 degrees apart, such that one point is on the positive x axis.
	 * Doing some moderate algebra and trigonometry, we get these coordinates:
	 * - Point 0: (cos   0, sin   0) = (+1, 0).
	 * - Point 1: (cos  72, sin  72) = (+(sqrt(5)-1)/4, +sqrt(10+2sqrt(5))/4).
	 * - Point 2: (cos 144, sin 144) = (-(sqrt(5)+1)/4, +sqrt(10-2sqrt(5))/4).
	 * - Point 3: (cos 216, sin 216) = (-(sqrt(5)+1)/4, -sqrt(10-2sqrt(5))/4).
	 * - Point 4: (cos 288, sin 288) = (+(sqrt(5)-1)/4, -sqrt(10+2sqrt(5))/4).
	 * (The inputs to cos and sin are given in degrees.)
	 * 
	 * This sequence of points is constructed so that the displacement vector from point k to point
	 * k+1 is equal to the displacement of making a anticlockwise move when facing direction k.
	 * For example, (point1 - point0) is the displacement of an anticlockwise move when facing north (dir=0).
	 * The actual displacement values are:
	 * - point1 - point0 = (+(sqrt(5)-5)/4, +sqrt(10+2sqrt(5))/4).
	 * - point2 - point1 = (-sqrt(5)/2, -sqrt(5-2sqrt(5))/2).
	 * - point3 - point2 = (0, -sqrt(10-2sqrt(5))/2).
	 * - point4 - point3 = (+sqrt(5)/2, -sqrt(5-2sqrt(5))/2).
	 * - point0 - point4 = (-(sqrt(5)-5)/4, +sqrt(10+2sqrt(5))/4).
	 * As for clockwise moves, simply take the displacements vectors above and negate the x values.
	 * The mapping of displacement vectors to direction states also needs to be negated modulo 5.
	 * 
	 * Altogether, we have this table of valid moves:
	 *    Direction | Move | x displacement |   y displacement
	 *   -----------+------+----------------+---------------------
	 *        0     | ACW  | +(sqrt(5)-5)/4 | +sqrt(10+2sqrt(5))/4
	 *        0     |  CW  | -(sqrt(5)-5)/4 | +sqrt(10+2sqrt(5))/4
	 *        1     | ACW  |   -sqrt(5)/2   | -sqrt(5-2sqrt(5))/2
	 *        1     |  CW  | +(sqrt(5)-5)/4 | -sqrt(5-2sqrt(5))/2
	 *        2     | ACW  |       0        | -sqrt(10-2sqrt(5))/2
	 *        2     |  CW  |   -sqrt(5)/2   | -sqrt(5-2sqrt(5))/2
	 *        3     | ACW  |   +sqrt(5)/2   | -sqrt(5-2sqrt(5))/2
	 *        3     |  CW  |       0        | -sqrt(10-2sqrt(5))/2
	 *        4     | ACW  | -(sqrt(5)-5)/4 | -sqrt(5-2sqrt(5))/2
	 *        4     |  CW  |   +sqrt(5)/2   | -sqrt(5-2sqrt(5))/2
	 * 
	 * As the robot moves, it adds up x components and y components to its displacement.
	 * - At any given time, the x coordinate can be described as a unique weighted integer
	 *   sum of sqrt(5)/2 and (sqrt(5)-5)/4, namely i*sqrt(5)/2 + j*(sqrt(5)-5)/4.
	 *   We could prove that these two irrational components cannot "simplify"
	 *   with each other - i.e. when (i, j) != (0, 0), the sum must be non-zero.
	 * - At any given time, the y coordinate can be described the dot product of
	 *   the vector (-sqrt(10-2sqrt(5))/2, sqrt(10+2sqrt(5))/4, -sqrt(5-2sqrt(5))/2)
	 *   with some particular vector of 3 natural numbers.
	 *   Note very importantly that there is a cancellation property in effect:
	 *   1*-sqrt(10-2sqrt(5))/2 + 2*+sqrt(10+2sqrt(5))/4 + 2*-sqrt(5-2sqrt(5))/2 = 0.
	 */
	
	private static final int LIMIT = 70;
	
	public String run() {
		Map<State,BigInteger> reachable = new HashMap<>();
		reachable.put(new State(0, 0, 0, 0, 0, 0), BigInteger.ONE);
		
		for (int i = 0; i < LIMIT; i++) {
			Map<State,BigInteger> newReachable = new HashMap<>();
			for (Map.Entry<State,BigInteger> entry : reachable.entrySet()) {
				State state = entry.getKey();
				BigInteger ways = entry.getValue();
				add(newReachable, state.moveAnticlockwise(), ways);
				add(newReachable, state.moveClockwise    (), ways);
			}
			reachable = newReachable;
		}
		
		BigInteger sum = BigInteger.ZERO;
		for (int dir = 0; dir < 5; dir++) {
			State st = new State(dir, 0, 0, 0, 0, 0);
			if (reachable.containsKey(st))
				sum = sum.add(reachable.get(st));
		}
		return sum.toString();
	}
	
	
	private static void add(Map<State,BigInteger> reachable, State state, BigInteger ways) {
		BigInteger temp = reachable.containsKey(state) ? reachable.get(state) : BigInteger.ZERO;
		reachable.put(state, temp.add(ways));
	}
	
	
	
	private static final class State {
		
		public final int direction;  // In the range [0, 5).
		
		public final int xComponent0;  // Any integer. For sqrt(5)/2.
		public final int xComponent1;  // Any integer. For (sqrt(5)-5)/4.
		
		public final int yComponent0;  // Non-negative. For -sqrt(10-2sqrt(5))/2.
		public final int yComponent1;  // Non-negative. For +sqrt(10+2sqrt(5))/4.
		public final int yComponent2;  // Non-negative. For -sqrt(5-2sqrt(5))/2.
		
		
		public State(int dir, int xComp0, int xComp1, int yComp0, int yComp1, int yComp2) {
			direction = dir;
			xComponent0 = xComp0;
			xComponent1 = xComp1;
			while (yComp0 >= 1 && yComp1 >= 2 && yComp2 >= 2) {
				yComp0 -= 1;
				yComp1 -= 2;
				yComp2 -= 2;
			}
			yComponent0 = yComp0;
			yComponent1 = yComp1;
			yComponent2 = yComp2;
		}
		
		
		public State moveAnticlockwise() {
			return new State(
				(direction + 1) % 5,
				xComponent0 + MOVE_TABLE[direction][0][0],
				xComponent1 + MOVE_TABLE[direction][0][1],
				yComponent0 + MOVE_TABLE[direction][0][2],
				yComponent1 + MOVE_TABLE[direction][0][3],
				yComponent2 + MOVE_TABLE[direction][0][4]);
		}
		
		
		public State moveClockwise() {
			return new State(
				(direction + 4) % 5,
				xComponent0 + MOVE_TABLE[direction][1][0],
				xComponent1 + MOVE_TABLE[direction][1][1],
				yComponent0 + MOVE_TABLE[direction][1][2],
				yComponent1 + MOVE_TABLE[direction][1][3],
				yComponent2 + MOVE_TABLE[direction][1][4]);
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
				yComponent1 == other.yComponent1 &&
				yComponent2 == other.yComponent2;
		}
		
		
		public int hashCode() {
			return Objects.hash(direction, xComponent0, xComponent1, yComponent0, yComponent1, yComponent2);
		}
		
		
		private static final int[][][] MOVE_TABLE = {
			{{ 0, +1, 0, 1, 0}, { 0, -1, 0, 1, 0}},
			{{-1,  0, 0, 0, 1}, { 0, +1, 0, 1, 0}},
			{{ 0,  0, 1, 0, 0}, {-1,  0, 0, 0, 1}},
			{{+1,  0, 0, 0, 1}, { 0,  0, 1, 0, 0}},
			{{ 0, -1, 0, 1, 0}, {+1,  0, 0, 0, 1}},
		};
		
	}
	
}
