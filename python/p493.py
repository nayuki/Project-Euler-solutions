# 
# Solution to Project Euler problem 493
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions, math


def compute():
	NUM_COLORS = 7
	BALLS_PER_COLOR = 10
	NUM_PICKED = 20
	DECIMALS = 9
	
	numerator = [0]
	
	def explore(remain, limit, history):
		if remain == 0:
			hist = list(history)
			while len(hist) < NUM_COLORS:
				hist.append(0)
			
			histogram = [0] * (BALLS_PER_COLOR + 1)
			for x in hist:
				histogram[x] += 1
			
			count = math.factorial(NUM_COLORS)
			for x in histogram:
				count = divide_exactly(count, math.factorial(x))
			
			for x in hist:
				count *= eulerlib.binomial(BALLS_PER_COLOR, x)
			
			distinctcolors = len(history)
			numerator[0] += count * distinctcolors
		
		elif len(history) < NUM_COLORS:
			for i in range(min(limit, remain), 0, -1):
				history.append(i)
				explore(remain - i, i, history)
				history.pop()
	
	explore(NUM_PICKED, BALLS_PER_COLOR, [])
	denominator = eulerlib.binomial(NUM_COLORS * BALLS_PER_COLOR, NUM_PICKED)
	ans = fractions.Fraction(numerator[0], denominator)
	return format_fraction(ans, DECIMALS)
	

def format_fraction(val, digits):
	if digits <= 0:
		raise ValueError()
	if val < 0:
		return "-" + format_fraction(-val, digits)
	s = str(round(val * 10**digits)).zfill(digits + 1)
	return f"{s[:-digits]}.{s[-digits:]}"


def divide_exactly(x, y):
	if x % y != 0:
		raise ValueError("Not divisible")
	return x // y


if __name__ == "__main__":
	print(compute())
