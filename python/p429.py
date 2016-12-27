# 
# Solution to Project Euler problem 429
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# Let n be an arbitrary positive integer. Suppose n is factorized as p1^k1 * p2^k2 * ... * {p_m}^{k_m},
# where the p's are prime and distinct (thus the k's are as large as possible).
# Let {p1^k1, p2^k2, ..., {p_m}^{k_m}} be the set of "maximal prime powers".
# Then all the unitary divisors of n are exactly all the subsets of maximal prime powers,
# where each subset is viewed as a product of its elements.
# 
# For n!, its prime factorization uses and only uses all prime numbers from 1 to n (inclusive).
# For each prime p, the number n! has exactly floor(n/p) + floor(n/p^2) + floor(n/p^3) + ... factors of p.
# Thus we can calculate the p's and k's quite easily.
# 
# To solve the remaining parts of the problem, we use dynamic programming.
# Suppose we have found all the unitary divisors that are products of maximal prime powers less than {p_i}^{k_i},
# and suppose this set is {a, b, c}. Then when we include {p_i}^{k_i} into consideration, we double the size of the set
# because now {a * {p_i}^{k_i}, b * {p_i}^{k_i}, c * {p_i}^{k_i}} are also unitary divisors.
def compute():
	LIMIT = 10**8
	MOD = 1000000009
	ans = 1
	for p in eulerlib.prime_generator(LIMIT):
		power = count_factors(LIMIT, p)
		ans *= 1 + pow(p, power * 2, MOD)
		ans %= MOD
	return str(ans)


# Returns the number of factors of p (prime) in factorial(n).
def count_factors(n, p):
	if n == 0:
		return 0
	else:
		return n // p + count_factors(n // p, p)


if __name__ == "__main__":
	print(compute())
