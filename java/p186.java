/* 
 * Solution to Project Euler problem 186
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p186 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p186().run());
	}
	
	
	public String run() {
		DisjointSet ds = new DisjointSet(1000000);
		LfgRandom rand = new LfgRandom();
		int i = 0;
		while (ds.size(524287) < 990000) {
			int caller = rand.next();
			int callee = rand.next();
			if (caller != callee) {
				ds.union(caller, callee);
				i++;
			}
		}
		return Integer.toString(i);
	}
	
	
	
	private static final class DisjointSet {
		
		private Node[] nodes;
		
		
		public DisjointSet(int size) {
			nodes = new Node[size];
			for (int i = 0; i < size; i++)
				nodes[i] = new Node();
		}
		
		
		private Node find(int i) {
			return find(nodes[i]);
		}
		
		
		private static Node find(Node node) {
			if (node.parent != node)
				node.parent = find(node.parent);  // Path compression
			return node.parent;
		}
		
		
		public void union(int i, int j) {
			Node x = find(i);
			Node y = find(j);
			if (x == y)
				return;
			if (x.rank == y.rank)
				x.rank++;
			else if (x.rank < y.rank) {
				Node z = x;
				x = y;
				y = z;
			}
			y.parent = x;
			x.size += y.size;
			y.size = 0;
		}
		
		
		public int size(int i) {
			return find(i).size;
		}
		
		
		private static final class Node {
			public Node parent;
			public int rank;
			public int size;
			
			public Node() {
				parent = this;
				rank = 0;
				size = 1;
			}
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
			if (k <= 55) {
				result = (int)((100003L - 200003L*k + 300007L*k*k*k) % 1000000);
				k++;
			} else
				result = (getHistory(24) + getHistory(55)) % 1000000;
			
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
