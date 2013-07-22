/* 
 * Solution to Project Euler problem 280
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public final class p280 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p280().run());
	}
	
	
	// Model the problem as a Markov process, and solve using dynamic programming
	public String run() {
		int[][] successors = new int[State.NUMBER_OF_STATES][];
		Set<State> allStates = State.listAllStates();
		for (State st : allStates) {
			Set<State> suc = st.getSuccessors();
			int[] sucId = new int[suc.size()];
			Iterator<State> it = suc.iterator();
			for (int i = 0; i < sucId.length; i++)
				sucId[i] = it.next().getId();
			successors[st.getId()] = sucId;
		}
		
		double sum = 0;
		double[] probs = new double[State.NUMBER_OF_STATES];
		probs[State.START_STATE.getId()] = 1;
		for (int i = 1; ; i++) {
			// Note: The done state has no outgoing neighbors, so its probability disappears in the next iteration
			double[] newProbs = new double[probs.length];
			for (int j = 0; j < probs.length; j++) {
				if (probs[j] == 0)
					continue;
				int[] suc = successors[j];
				for (int k : suc)
					newProbs[k] += probs[j] / suc.length;
			}
			
			double doneNow = newProbs[State.DONE_STATE.getId()];
			if (i > 44 && doneNow < 1e-20)  // Note: Minimum completion is 44 steps
				break;
			sum += doneNow * i;
			probs = newProbs;
		}
		return String.format("%.6f", sum);
	}
	
	
	
	private static class State {
		
		public static final int NUMBER_OF_STATES = 5 * 5 * (1 << 11) + 1;
		
		public static State START_STATE = new State(false, 2, 2, new boolean[]{false, false, false, false, false, true, true, true, true, true, false});
		public static State DONE_STATE  = new State(true , 0, 0, new boolean[]{true, true, true, true, true, false, false, false, false, false, false});
		
		
		public static Set<State> listAllStates() {
			HashSet<State> result = new HashSet<State>();
			for (int i = 0; i < (1 << 11); i++) {
				if (Integer.bitCount(i) != 5)
					continue;
				for (int y = 0; y < 5; y++) {
					for (int x = 0; x < 5; x++) {
						boolean[] seed = new boolean[11];
						for (int j = 0; j < seed.length; j++)
							seed[j] = ((i >>> j) & 1) != 0;
						result.add(new State(false, x, y, seed));
					}
				}
			}
			result.add(DONE_STATE);
			return result;
		}
		
		
		private boolean isDone;  // Note: When the ant is done, we neglect its position - so there's only 1 done state, not 5
		private int antX;
		private int antY;
		private boolean[] hasSeed;  // Indices 0 to 4 are for the top row, 5 to 9 for the bottom row, and 10 for the ant. Exactly 5 elements are true.
		
		
		public State(boolean done, int x, int y, boolean[] seed) {
			isDone = done;
			antX = x;
			antY = y;
			hasSeed = seed;
		}
		
		
		// Returns a unique number for this state, in the range [0, NUMBER_OF_STATES)
		public int getId() {
			if (isDone)
				return 5 * 5 * (1 << hasSeed.length);
			
			int result = 0;
			for (int i = 0; i < hasSeed.length; i++)
				result |= (hasSeed[i] ? 1 : 0) << i;
			result = antX + antY * 5 + result * 25;
			return result;
		}
		
		
		public Set<State> getSuccessors() {
			HashSet<State> result = new HashSet<State>();
			if (!isDone) {
				tryAddSuccessor(-1, 0, result);
				tryAddSuccessor(+1, 0, result);
				tryAddSuccessor(0, -1, result);
				tryAddSuccessor(0, +1, result);
			}
			return result;
		}
		
		
		private void tryAddSuccessor(int dx, int dy, Set<State> result) {
			int x = antX + dx;
			int y = antY + dy;
			if (x < 0 || x >= 5 || y < 0 || y >= 5)
				return;
			
			boolean[] seed = hasSeed.clone();
			boolean done = false;
			if (!seed[10] && y == 4 && seed[5 + x]) {  // Pick up seed
				seed[5 + x] = false;
				seed[10] = true;
			} else if (seed[10] && y == 0 && !seed[x]) {  // Drop seed
				seed[10] = false;
				seed[x] = true;
				done = seed[0] && seed[1] && seed[2] && seed[3] && seed[4];
			}
			result.add(new State(done, x, y, seed));
		}
		
	}
	
}
