/* 
 * Solution to Project Euler problem 128
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p128 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p128().run());
	}
	
	
	public String run() {
		return Long.toString(findSolution(2000));
	}
	
	
	private static long findSolution(int targetIndex) {  // 1-based counting
		int count = 1;  // Because PD(1) = 3
		boolean printed = false;
		for (int layer = 1; ; layer++) {
			for (int sextant = 0; sextant < 6; sextant++) {
				for (int offset = 0; offset < layer; offset++) {
					long i = lsoToNumber(packLso(layer, sextant, offset));
					if (!printed) {  // Progress reporting for the impatient
						System.out.println(count + " " + i);
						printed = true;
					}
					if (countPrimeNeighbors(i) == 3) {
						count++;
						printed = false;
						if (count == targetIndex)
							return i;
					}
					if (offset == 1 && layer > 2)  // Skip to penultimate offset
						offset = layer - 2;
				}
			}
		}
	}
	
	
	/* 
	 * Coordinate system of (u, v):
	 *         +u
	 *   +u+v  |   -v
	 *       \ | /
	 *         *
	 *       / | \
	 *    +v   |  -u-v
	 *        -u
	 * 
	 * Actual coordinates:
	 *             (+2,+0)
	 *        (+2,+1)   (+1,-1)
	 *   (+2,+2)   (+1,+0)   (+0,-2)
	 *        (+1,+1)   (+0,-1)
	 *   (+1,+2)   (+0,+0)   (-1,-2)
	 *        (+0,+1)   (-1,-1)
	 *   (+0,+2)   (-1,+0)   (-2,-2)
	 *        (-1,+1)   (-2,-1)
	 *             (-2,+0)
	 * 
	 * Layer system:
	 *            3
	 *         3     3
	 *      3     2     3
	 *   3     2     2     3
	 *      2     1     2
	 *   3     1     1     3
	 *      2     0     2
	 *   3     1     1     3
	 *      2     1     2
	 *   3     2     2     3
	 *      3     2     3
	 *         3     3
	 *            3
	 * 
	 * Sextant system:
	 *           0
	 *        0     5
	 *     0     0     5
	 *  1     0     5     5
	 *     1     0     5
	 *  1     1     5     4
	 *     1     0     4
	 *  1     2     4     4
	 *     2     3     4
	 *  2     2     3     4
	 *     2     3     3
	 *        2     3
	 *           3
	 * 
	 * Offset system:
	 *            0
	 *         1     2
	 *      2     0     1
	 *   0     1     1     0
	 *      0     0     0
	 *   1     0     0     2
	 *      1     0     1
	 *   2     0     0     1
	 *      0     0     0
	 *   0     1     1     0
	 *      1     0     2
	 *         2     1
	 *            0
	 * 
	 * Note: LSO stands for layer, sextant, offset.
	 */
	
	private static long numberToLso(long n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		
		int layer, sextant, offset;
		if (n == 1) {
			layer = 0;
			sextant = 0;
			offset = 0;
		} else {
			n -= 2;
			layer = 1;
			for (int layerSize = layer * 6; n >= layerSize; layerSize += 6) {
				if (layer > Integer.MAX_VALUE / 6)
					throw new ArithmeticException();
				n -= layerSize;
				layer++;
			}
			offset = (int)(n % layer);
			sextant = (int)(n / layer);
		}
		return packLso(layer, sextant, offset);
	}
	
	
	private static long lsoToCoordinate(long lso) {
		int u, v;
		if (lso == 0) {
			u = 0;
			v = 0;
			
		} else {
			int layer = (int)(lso >>> 33);
			int sextant = (int)(lso >>> 30) & 7;
			int offset = (int)lso & ((1 << 30) - 1);
			if (layer < 0 || sextant >= 6 || offset < 0 || offset >= layer)
				throw new IllegalArgumentException();
			
			switch (sextant) {
				case 0:  u = layer;           v = offset;          break;
				case 1:  u = layer - offset;  v = layer;           break;
				case 2:  u = -offset;         v = layer - offset;  break;
				case 3:  u = -layer;          v = -offset;         break;
				case 4:  u = offset - layer;  v = -layer;          break;
				case 5:  u = offset;          v = offset - layer;  break;
				default:  throw new AssertionError();
			}
		}
		return packCoordinate(u, v);
	}
	
	
	private static long coordinateToLso(long uv) {
		int u = (int)(uv >>> 32), v = (int)uv;
		if (u == 0 && v == 0)
			return 0;
		
		int sextant, layer, offset;
		if (u > 0 && v >= 0 && v < u) {
			sextant = 0;
			layer = u;
			offset = v;
		} else if (v > 0 && u > 0 && u <= v) {
			sextant = 1;
			layer = v;
			offset = layer - u;
		} else if (u <= 0 && v > 0) {
			sextant = 2;
			layer = v - u;
			offset = -u;
		} else if (u < 0 && v <= 0 && v > u) {
			sextant = 3;
			layer = -u;
			offset = -v;
		} else if (v < 0 && u < 0 && u >= v) {
			sextant = 4;
			layer = -v;
			offset = u - v;
		} else if (u >= 0 && v < 0) {
			sextant = 5;
			layer = u - v;
			offset = u;
		} else
			throw new AssertionError();
		return packLso(layer, sextant, offset);
	}
	
	
	private static long lsoToNumber(long lso) {
		if (lso == 0)
			return 1;
		
		int layer = (int)(lso >>> 33);
		int sextant = (int)(lso >>> 30) & 7;
		int offset = (int)lso & ((1 << 30) - 1);
		if (layer < 0 || sextant >= 6 || offset < 0 || offset >= layer)
			throw new IllegalArgumentException();
		
		long n = offset + sextant * layer + 2;
		for (int i = 1; i < layer; i++)
			n += i * 6;
		return n;
	}
	
	
	private static long packLso(int layer, int sextant, int offset) {
		return (long)layer << 33 | (long)sextant << 30 | offset;  // All parts are unsigned
	}
	
	
	private static long packCoordinate(int u, int v) {
		return (long)u << 32 | (v & 0xFFFFFFFFL);  // Both parts are signed int32
	}
	
	
	/* Other functions */
	
	private static int countPrimeNeighbors(long n) {
		long uv = lsoToCoordinate(numberToLso(n));
		int u = (int)(uv >>> 32), v = (int)uv;
		int count = 0;
		for (int[] delta : HEX_NEIGHBOR_DELTAS) {
			int x = u + delta[0];
			int y = v + delta[1];
			long neigh = lsoToNumber(coordinateToLso(packCoordinate(x, y)));
			if (isPrime(Math.abs(neigh - n)))
				count++;
		}
		return count;
	}
	
	
	private static boolean isPrime(long x) {
		if (x < 0)
			throw new IllegalArgumentException("Negative number");
		if (x < 6)
			return x == 2 || x == 3 || x == 5;
		else if (x % 2 == 0 || x % 3 == 0 || x % 5 == 0)
			return false;
		else {
			for (long i = 7, end = Library.sqrt(x); i <= end; i += 6) {
				if (x % i == 0 || x % (i + 4) == 0)  // Wheel factorization
					return false;
			}
			return true;
		}
	}
	
	
	private static final int[][] HEX_NEIGHBOR_DELTAS = {{-1,0}, {+1,0}, {0,-1}, {0,+1}, {-1,-1}, {+1,+1}};
	
}
