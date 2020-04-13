# 
# Solution to Project Euler problem 71
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# We consider each (integer) denominator d from 1 to 1000000 by brute force.
# For a given d, what is the largest integer n such that n/d < 3/7?
# 
# - If d is a multiple of 7, then the integer n' = (d / 7) * 3 satisfies n'/d = 3/7.
#   Hence we choose n = n' - 1 = (d / 7) * 3 - 1, so that n/d < 3/7.
#   Since (d / 7) * 3 is already an integer, it is equal to floor(d * 3 / 7),
#   which will unifie with the next case. Thus n = floor(d * 3 / 7) - 1.
# - Otherwise d is not a multiple of 7, so choosing n = floor(d * 3 / 7)
#   will automatically satisfy n/d < 3/7, and be the largest possible n
#   due to the definition of the floor function.
# 
# When we choose n in this manner, it might not be coprime with d. In other words,
# the simplified form of the fraction n/d might have a denominator smaller than d.
# 
# Let's process denominators in ascending order. Each denominator generates a pair
# of integers (n, d) that conceptually represents a fraction, without simplification.
# Whenever the current value of n/d is strictly larger than the previously saved value,
# we save this current value of (n, d).
# 
# If we handle denominators in this way - starting from 1, counting up consecutively -
# then it is guaranteed that our final saved pair (n, d) is in lowest terms. This is
# because if (n, d) is not in lowest terms, then its reduced form (n', d') would have
# been saved when the smaller denominator d' was processed, and because n/d is
# not larger than n'/d' (they are equal), the saved value would not be overwritten.
# Hence in this entire computation we can avoid explicitly simplifying any fraction at all.
def compute():
	LIMIT = 1000000
	maxnumer = 0
	maxdenom = 1
	for d in range(1, LIMIT + 1):
		n = d * 3 // 7
		if d % 7 == 0:
			n -= 1
		if n * maxdenom > d * maxnumer:  # n/d > maxdenom/maxnumer
			maxnumer = n
			maxdenom = d
	return str(maxnumer)


if __name__ == "__main__":
	print(compute())
