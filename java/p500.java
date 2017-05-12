/* 
 * Solution to Project Euler problem 500
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.PriorityQueue;
import java.util.Queue;


public final class p500 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p500().run());
	}
	
	
	private static final int POWER = 500500;
	private static final long MODULUS = 500500507;
	
	public String run() {
		Queue<Item> queue = new PriorityQueue<>();
		int largestPrime = 2;
		queue.add(new Item(largestPrime));
		
		long product = 1;
		for (int i = 0; i < POWER; i++) {
			Item item = queue.remove();
			product *= item.nextPower % MODULUS;
			product %= MODULUS;
			item.advance();
			queue.add(item);
			
			if (item.prime == largestPrime) {
				do largestPrime++;
				while (!Library.isPrime(largestPrime));
				queue.add(new Item(largestPrime));
			}
		}
		
		return Long.toString(product);
	}
	
	
	
	private static final class Item implements Comparable<Item> {
		
		public final int prime;
		public long nextPower;
		
		
		public Item(int prime) {
			if (prime < 2)
				throw new IllegalArgumentException();
			this.prime = prime;
			nextPower = prime;
		}
		
		
		public void advance() {
			nextPower *= nextPower;
		}
		
		
		public int compareTo(Item other) {
			return Long.compare(nextPower, other.nextPower);
		}
		
	}
	
}
