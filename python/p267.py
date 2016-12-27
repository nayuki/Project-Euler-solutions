# 
# Solution to Project Euler problem 267
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions, math, sys


# When you win a coin toss, your capital is multiplied by (1 + 2f).
# Whenever you lose, your capital is multiplied by (1 - f).
# Thus the game's result is independent of the order of wins and losses;
# what matters is only the total numbers of each outcome.
# 
# Suppose you have n tosses and w wins. Then there are n - w losses.
# By the binomial theorem, this outcome happens (n choose w) times out of 2^n.
# Moreover, the final capital is 1 * (1 + 2f)^w * (1 - f)^(n - w).
# 
# Some parts of this algorithm use accurate computations:
# - Sum of binomial coefficients in bigint, for the probability.
# - Conversion of the probability bigint fraction to decimal string.
# Some parts are inaccurate or are based on heuristics:
# - Calculating the final capital using floating-point arithmetic,
#   for each bet proportion and number of wins+losses.
# - Sampling the continuous input interval of [0.0, 1.0]
#   to try to maximize the value of the function.
# Overall this solution is not provably mathematically correct.
def compute():
	# Heuristic sampling algorithm.
	# At level 1 we test {1/2}. At level 2 we test {1/4, 3/4}.
	# At level 3 we test {1/8, 3/8, 5/8, 7/8}. Et cetera.
	TRIALS = 1000
	maxindex = -1
	prevchangelevel = 1
	level = 1
	while level - prevchangelevel <= 8:
		scaler = 0.5**level
		for i in range(1, 1 << level, 2):
			index = calc_billionaire_probability(i * scaler, TRIALS)
			if index > maxindex:
				maxindex = index
				prevchangelevel = level
		level += 1
	
	# Calculate the cumulative probability: binomialSum = sum (n choose k) for 0 <= k < maxIndex
	binomialsum = sum(eulerlib.binomial(TRIALS, i) for i in range(maxindex))
	return round_to_decimal(fractions.Fraction(binomialsum, 1 << TRIALS), 12)


# Returns the cumulative binomial probability index.
def calc_billionaire_probability(betproportion, trials):
	initcapital = 1.0
	logbillionaire = math.log(1.0e9)
	i = 0
	while i <= trials:  # Number of losses
		# Need to take logarithms because Python's ** operator and math.pow()
		# would raise an exception on overflow instead of returning infinity
		logfinalcapital = math.log(initcapital)
		logfinalcapital += math.log(1.0 - betproportion) * i
		logfinalcapital += math.log(1.0 + betproportion * 2) * (trials - i)
		if logfinalcapital < logbillionaire:
			break
		i += 1
	return i  # Range [0, TRIALS + 1]


# Converts a fraction to a correctly rounded decimal string.
def round_to_decimal(fracnum, places):
	assert places > 0
	if fracnum < 0:
		return "-" + round_to_decimal(-fracnum, places)
	
	# Round half to even
	fracnum *= 10**places
	if sys.version_info.major == 2:
		rounded = fracnum.numerator // fracnum.denominator
		frac = fracnum - rounded
		HALF = fractions.Fraction(1, 2)
		if frac > HALF or (frac == HALF and rounded % 2 == 1):
			rounded += 1
	else:
		rounded = round(fracnum)
	
	s = str(rounded).zfill(places + 1)
	return s[ : -places] + "." + s[-places : ]


if __name__ == "__main__":
	print(compute())
