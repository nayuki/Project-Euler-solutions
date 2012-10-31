/* 
 * Solution to Project Euler problem 186
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p186 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p186().run());
	}
	
	
	public String run() {
		DisjointSets ds = new DisjointSets(1000000);
		LfgRandom random = new LfgRandom();
		int i = 0;
		while (ds.size(524287) < 990000) {
			int caller = random.next();
			int callee = random.next();
			if (caller != callee) {
				ds.union(caller, callee);
				i++;
			}
		}
		return Integer.toString(i);
	}
	
	
	
	private static final class DisjointSets {
		
		private Node[] nodes;
		
		
		public DisjointSets(int size) {
			nodes = new Node[size];
			for (int i = 0; i < size; i++)
				nodes[i] = new Node();
		}
		
		
		public Node find(int i) {
			Node node = nodes[i];
			if (node.parent == node)
				return node;
			else {
				Node temp = node;
				while (temp.parent != temp)
					temp = temp.parent;
				node.parent = temp;  // Path compression
				temp.size += node.size;
				node.size = 0;
				return temp;
			}
		}
		
		
		public void union(int i, int j) {
			Node x = find(i);
			Node y = find(j);
			if (x == y)
				return;
			else if (x.rank < y.rank) {
				x.parent = y;
				y.size += x.size;
				x.size = 0;
			} else if (x.rank > y.rank) {
				y.parent = x;
				x.size += y.size;
				y.size = 0;
			} else {
				x.parent = y;
				y.rank++;
				y.size += x.size;
				x.size = 0;
			}
		}
		
		
		public int size(int i) {
			Node node = nodes[i];
			while (node.parent != node)
				node = node.parent;
			return node.size;
		}
		
	}
	
	
	private static final class Node {
		
		public Node parent;
		
		public int rank;
		
		public int size;  // The size of the set that this node belongs to, valid if this node is the representative
		
		
		public Node() {
			parent = this;
			rank = 0;
			size = 1;
		}
		
	}
	
	
	
	// Lagged Fibonacci generator
	private static final class LfgRandom {
		
		private int k;
		
		private int[] history;  // Circular buffer
		private int index;
		
		
		public LfgRandom() {
			k = 1;
			history = new int[55];
			index = 0;
		}
		
		
		public int next() {
			int result;
			if (k <= 55) result = (int)((100003L - 200003L*k + 300007L*k*k*k) % 1000000);
			else result = (getHistory(24) + getHistory(55)) % 1000000;
			k++;
			
			history[index] = result;
			index++;
			if (index == history.length)
				index = 0;
			
			return result;
		}
		
		
		private int getHistory(int n) {
			int i = index - n;
			if (i < 0)
				i += history.length;
			return history[i];
		}
		
	}
	
}
