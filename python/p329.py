# 
# Solution to Project Euler problem 329
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions


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
	for i in range(START_NUM, END_NUM + 1):
		for j in range(NUM_TRIALS):
			pos = i
			trialnumerator = 1
			if isprime[pos] == (CROAK_SEQ[0] == 'P'):
				trialnumerator *= 2
			for k in range(NUM_JUMPS):
				if pos <= START_NUM:
					pos += 1
				elif pos >= END_NUM:
					pos -= 1
				elif (j >> k) & 1 == 0:
					pos += 1
				else:
					pos -= 1
				if isprime[pos] == (CROAK_SEQ[k + 1] == 'P'):
					trialnumerator *= 2
			globalnumerator += trialnumerator
	
	globaldenominator = (END_NUM + 1 - START_NUM) * 2**NUM_JUMPS * 3**len(CROAK_SEQ)
	ans = fractions.Fraction(globalnumerator, globaldenominator)
	return str(ans)


if __name__ == "__main__":
	print(compute())
