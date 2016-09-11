# 
# Solution to Project Euler problem 271
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	# Note: 13082761331670030 = product of lowest 14 primes
	# Find solutions to x^3 = 1 mod p, for each prime factor p
	FACTORS = (2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43)
	factorsols = [
		[j for j in range(fact) if pow(j, 3, fact) == 1]
		for fact in FACTORS]
	
	# Try all possibilities recursively
	def build_and_sum_solutions(i, x, mod):
		if i == len(FACTORS):
			return x
		else:
			fact = FACTORS[i]
			return sum(
				build_and_sum_solutions(i + 1, chinese_remainder_theorem(x, mod, sol, fact), mod * fact)
				for sol in factorsols[i])
	
	# The recursive algorithm generates all solutions, but the problem statement excludes 1
	ans = build_and_sum_solutions(0, 0, 1) - 1
	return str(ans)


# Given that for an unknown x, x = a mod p and x = b mod q (where p and q are coprime),
# returns an integer 0 <= x < p*q satisfying these congruences.
def chinese_remainder_theorem(a, p, b, q):
	return (a + (b - a) * eulerlib.reciprocal_mod(p % q, q) * p) % (p * q)


if __name__ == "__main__":
	print(compute())
