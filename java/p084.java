/* 
 * Solution to Project Euler problem 84
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import java.util.Arrays;
import java.util.Random;


public final class p084 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p084().run());
	}
	
	
	private Random random = new Random();
	
	/* 
	 * This is a statistical sampling approximation algorithm that simply simulates the game for a fixed number of dice rolls.
	 * An exact algorithm would involve calculating the eigenvector of the largest eigenvalue of the transition matrix (which is practical),
	 * but averaging over all possible permutations of both the Chance and Community Chest decks (which is computationally infeasible).
	 */
	public String run() {
		final int[] visitCounts = new int[40];
		
		CardDeck chance = new CardDeck(16);
		CardDeck communityChest = new CardDeck(16);
		int consecutiveDoubles = 0;
		int location = 0;
		for (int i = 0; i < 100000000; i++) {
			// Roll tetrahedral dice
			int die0 = random.nextInt(4) + 1;
			int die1 = random.nextInt(4) + 1;
			if (die0 == die1)
				consecutiveDoubles++;
			else
				consecutiveDoubles = 0;
			if (consecutiveDoubles < 3)
				location = (location + die0 + die1) % 40;
			else {
				location = 30;
				consecutiveDoubles = 0;
			}
			
			// Process actions for some locations
			switch (location) {
				case 7: case 22: case 36:  // Chance
					switch (chance.nextCard()) {
						case 0:  location =  0;  break;
						case 1:  location = 10;  break;
						case 2:  location = 11;  break;
						case 3:  location = 24;  break;
						case 4:  location = 39;  break;
						case 5:  location =  5;  break;
						case 6: case 7:  // Next railway
							location = (location + 5) / 10 % 4 * 10 + 5;
							break;
						case 8:  // Next utility
							location = location > 12 && location < 28 ? 28 : 12;
							break;
						case 9:
							location -= 3;
							break;
						default:
							break;
					}
					break;
				case 30:  // Go to jail
					location = 10;
					break;
				default:
					break;
			}
			switch (location) {
				case 2: case 17: case 33:  // Community chest
					switch (communityChest.nextCard()) {
						case 0 :  location =  0;  break;
						case 1 :  location = 10;  break;
						default:  break;
					}
					break;
				default:
					break;
			}
			
			visitCounts[location]++;
		}
		
		// Embed index into count, invert so that maximum becomes minimum
		for (int i = 0; i < visitCounts.length; i++)
			visitCounts[i] = ~visitCounts[i] << 6 | i;
		Arrays.sort(visitCounts);
		String result = "";
		for (int i = 0; i < 3; i++)
			result += String.format("%02d", visitCounts[i] & 0x3F);
		return result;
	}
	
	
	private final class CardDeck {
		
		private int[] cards;
		private int index;
		
		
		public CardDeck(int size) {
			cards = new int[size];
			for (int i = 0; i < cards.length; i++)
				cards[i] = i;
			index = size;
		}
		
		
		public int nextCard() {
			if (index == cards.length) {
				// Fisher-Yates shuffle
				for (int i = cards.length - 1; i >= 0; i--) {
					int j = random.nextInt(i + 1);
					int temp = cards[i];
					cards[i] = cards[j];
					cards[j] = temp;
				}
				index = 0;
			}
			int result = cards[index];
			index++;
			return result;
		}
		
	}
	
}
