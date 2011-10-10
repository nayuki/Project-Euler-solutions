/* 
 * Solution to Project Euler problem 186
 * By Nayuki Minase
 */


public class p186 {
	
	public static void main(String[] args) {
		// Binary search
		int iters = 1;
		while (connectedness(iters) < 990000)
			iters *= 2;
		
		iters /= 2;
		for (int i = iters / 2; i != 0; i /= 2) {
			if (connectedness(iters + i) < 990000)
				iters += i;
		}
		System.out.println(iters + 1);
	}
	
	
	private static int connectedness(int iters) {
		LfgRandom random = new LfgRandom();
		DisjointSets ds = new DisjointSets(1000000);
		for (int i = 0; i < iters; ) {
			int caller = random.next();
			int callee = random.next();
			if (caller == callee)
				continue;
			i++;
			ds.union(caller, callee);
		}
		
		int count = 0;
		for (int i = 0; i < 1000000; i++) {
			if (ds.find(i) == ds.find(524287))
				count++;
		}
		return count;
	}
	
	
	
	private static class DisjointSets {
		
		private Node[] nodes;
		
		
		public DisjointSets(int size) {
			nodes = new Node[size];
			for (int i = 0; i < size; i++)
				nodes[i] = new Node(i);
		}
		
		
		public Node find(int i) {
			Node node = nodes[i];
			while (node.parent != node)
				node = node.parent;
			return node;
		}
		
		
		public void union(int i, int j) {
			Node x = find(i);
			Node y = find(j);
			if (x == y)
				return;
			else if (x.rank < y.rank)
				x.parent = y;
			else if (x.rank > y.rank)
				y.parent = x;
			else {
				x.parent = y;
				y.rank++;
			}
		}
		
	}
	
	
	private static class Node {
		
		public final int id;
		
		public Node parent;
		
		public int rank;
		
		
		public Node(int id) {
			this.id = id;
			parent = this;
			rank = 0;
		}
		
	}
	
	
	
	// Lagged Fibonacci generator
	private static class LfgRandom {
		
		private int k;
		
		private int[] history;
		
		
		public LfgRandom() {
			k = 1;
			history = new int[55];
		}
		
		
		public int next() {
			int result;
			if (k <= 55) result = (int)((100003L - 200003L*k + 300007L*k*k*k) % 1000000);
			else result = (history[23] + history[54]) % 1000000;
			System.arraycopy(history, 0, history, 1, history.length - 1);
			history[0] = result;
			k++;
			return result;
		}
		
	}
	
}
