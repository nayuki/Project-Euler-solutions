# 
# Solution to Project Euler problem 271
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# First we observe that the modulus 13082761331670030 can be factorized as
# 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23 * 29 * 31 * 37 * 41 * 43,
# which happens to be the product of the first 14 prime numbers.
# 
# Due to the laws of modular arithmetic, if x^3 = 1 mod (n*k),
# then it is also true that x^3 = 1 mod n and x^3 = 1 mod k.
# Hence the problem statement is equivalent to finding all x in the range (1, n) such that
# (x^3 = 1 mod 2) and (x^3 = 1 mod 3) and (x^3 = 1 mod 5) and ... and (x^3 = 1 mod 43).
# 
# When the main congruence of x^3 = 1 mod 13082761331670030 is broken up into a set of sub-congruences,
# because the sub-congruences have tiny moduli they are easy to solve by brute force. That is to say,
# for x^3 = 1 mod n, we can find all the solutions for x in the range [1, k) just by testing all possibilities.
# For example:
# - x^3 = 1 mod  2 has the solutions (for 1 <= x <  2) of {1}.
# - x^3 = 1 mod  7 has the solutions (for 1 <= x <  7) of {1, 2, 4}.
# - x^3 = 1 mod 11 has the solutions (for 1 <= x < 11) of {1}.
# - x^3 = 1 mod 43 has the solutions (for 1 <= x < 43) of {1, 6, 36}.
# 
# By the Chinese remainder theorem, the set of solutions for x^3 = 1 mod (n*k) has a bijection with
# the set of ordered pairs of solutions for (x^3 = 1 mod n, x^3 = 1 mod k). Furthermore, if we know
# all solutions of (x^3 = 1 mod n) and (x^3 = 1 mod k), then we can apply the CRT on these numbers to
# compute all the solutions of x^3 = 1 mod (n*k). Using this fact, we build up the full set of solutions
# from the smallest factor to the largest factor.
def compute():
	FACTORS = (2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43)
	factorsols = [
		[j for j in range(fact) if pow(j, 3, fact) == 1]
		for fact in FACTORS]
	
	def build_and_sum_solutions(i, x, mod):
		if i == len(FACTORS):
			return x
		else:
			fact = FACTORS[i]
			return sum(
				build_and_sum_solutions(i + 1, chinese_remainder_theorem(x, mod, sol, fact), mod * fact)
				for sol in factorsols[i])
	
	# Note: The recursive algorithm generates all solutions, but the problem statement excludes 1
	ans = build_and_sum_solutions(0, 0, 1) - 1
	return str(ans)


# Assuming that p and q are coprime, 0 <= a < p, and 0 <= b < q, this returns the unique
# integer x in the range [0, p*q) such that x satisfies (x = a mod p) and (x = b mod q).
def chinese_remainder_theorem(a, p, b, q):
	return (a + (b - a) * pow(p, -1, q) * p) % (p * q)


if __name__ == "__main__":
	print(compute())
