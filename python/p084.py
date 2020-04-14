# 
# Solution to Project Euler problem 84
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import random


# This is a statistical sampling approximation algorithm that simply simulates the game for a fixed number of dice rolls.
# An exact algorithm would involve calculating the eigenvector of the largest eigenvalue of the transition matrix (which is practical),
# but averaging over all possible permutations of both the Chance and Community Chest decks (which is computationally infeasible).
def compute():
	TRIALS = 10**7
	
	visitcounts = [0] * 40
	
	chance = CardDeck(16)
	communitychest = CardDeck(16)
	consecutivedoubles = 0
	location = 0
	
	for i in range(TRIALS):
		# Roll tetrahedral dice
		die0 = random.randint(1, 4)
		die1 = random.randint(1, 4)
		consecutivedoubles = (consecutivedoubles + 1) if (die0 == die1) else 0
		if consecutivedoubles < 3:
			location = (location + die0 + die1) % 40
		else:
			location = 30
			consecutivedoubles = 0
		
		# Process actions for some locations
		if location in (7, 22, 36):  # Chance
			card = chance.next_card()
			if   card == 0:  location =  0
			elif card == 1:  location = 10
			elif card == 2:  location = 11
			elif card == 3:  location = 24
			elif card == 4:  location = 39
			elif card == 5:  location =  5
			elif card in (6, 7):  # Next railway
				location = (location + 5) // 10 % 4 * 10 + 5
			elif card == 8:  # Next utility
				location = 28 if (12 < location < 28) else 12
			elif card == 9:
				location -= 3
			else:
				pass
		elif location == 30:  # Go to jail
			location = 10
		else:
			pass
		
		if location in (2, 17, 33):  # Community chest
			card = communitychest.next_card()
			if   card == 0:  location =  0
			elif card == 1:  location = 10
		
		visitcounts[location] += 1
	
	temp = sorted(enumerate(visitcounts), key=(lambda ic: -ic[1]))
	ans = "".join(f"{i:02}" for (i, c) in temp[ : 3])
	return str(ans)



class CardDeck:

	def __init__(self, size):
		self.cards = list(range(size))
		self.index = size
	
	def next_card(self):
		if self.index == len(self.cards):
			random.shuffle(self.cards)
			self.index = 0
		result = self.cards[self.index]
		self.index += 1
		return result


if __name__ == "__main__":
	print(compute())
