# 
# Solution to Project Euler problem 329
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions


# We simulate by brute force all the possible paths that the frog can take, and
# multiply and sum the probabilities along the way. The frog is initially positioned
# at one of the 500 squares and makes a croak. Then it jumps left or right 14 times
# and makes a croak after each jump (thus there are 15 croaks per simulation).
# Overall, there are exactly 500 * 2^14 = 8.192 million paths to examine.
# 
# Let's look at how the probabilities work for a particular path:
# - Because the frog starts at each square with uniform probability and jumps left/right
#   with equal probability, the probability of any particular path is 1 / (500 * 2^14).
# - For any given path, it can generate exactly 2^15 croak sequences, but each sequence
#   doesn't have the same probability. Look at the sequence of numbers that the frog
#   lands on in this path, e.g. {2, 1, 0, 1, 2, 3, 4, 3, ...}. Now look at sequence of
#   whether each of these numbers is prime, i.e. {P, N, N, N, P, P, N, P, ...}.
#   The probability of the desired croak sequence is equal to (2/3)^(number of indexes
#   where the croak letter matches the primeness sequence) * (1/3)^(number of indexes
#   where the croak letter mismatches the primeness sequence). We can simplify this
#   expression to just 2^(number of matching indexes) / 3^15.
# 
# So for all the 8192000 paths, we calculate the probability that each path matches
# the target croak sequence, sum all these probabilities, and divide by 8192000.
# We perform calculations in fraction or integer form (not floating point),
# and extract the numerator and denominator of the final simplified result.
# 
# Note that this analysis is correct even considering the rule that when
# the frog is at one of the range endpoints, it only has one possible move
# (e.g. if the frog is at square 1, then it must move to square 2).
def compute():
	START_NUM = 1
	END_NUM = 500
	CROAK_SEQ = "PPPPNNPPPNPPNPN"
	assert 0 <= START_NUM < END_NUM
	assert 1 <= len(CROAK_SEQ)
	
	NUM_JUMPS = len(CROAK_SEQ) - 1
	NUM_TRIALS = 2**NUM_JUMPS
	
	globalnumerator = 0
	isprime = eulerlib.list_primality(END_NUM)
	
	# For each starting square
	for i in range(START_NUM, END_NUM + 1):
		# For each sequence of jumps
		for j in range(NUM_TRIALS):
			
			# Set initial position and croak
			pos = i
			trialnumerator = 1
			if isprime[pos] == (CROAK_SEQ[0] == 'P'):
				trialnumerator *= 2
			
			# Simulate each jump and croak
			for k in range(NUM_JUMPS):
				if pos <= START_NUM:
					pos += 1  # Forced move
				elif pos >= END_NUM:
					pos -= 1  # Forced move
				elif (j >> k) & 1 == 0:
					pos += 1  # Chosen move
				else:
					pos -= 1  # Chosen move
				
				# Multiply the running probability by 2/3 if primeness of current position
				# matches croak sequence at current index, otherwise multiply by 1/3
				if isprime[pos] == (CROAK_SEQ[k + 1] == 'P'):
					trialnumerator *= 2
			globalnumerator += trialnumerator
	
	# Calculate final probability fraction
	globaldenominator = (END_NUM + 1 - START_NUM) * 2**NUM_JUMPS * 3**len(CROAK_SEQ)
	ans = fractions.Fraction(globalnumerator, globaldenominator)
	return str(ans)


if __name__ == "__main__":
	print(compute())
