# 
# Solution to Project Euler problem 357
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# From the problem statement, we are given the search range of 1 <= n <= 10^8.
# For a given integer n, we know that every divisor d of n is in the range [1, n].
# 
# It is beneficial to precompute a table of primes in this application, but what is
# the maximum value of f(d) = d + n/d for an arbitrary divisor d for 1 <= n <= 10^8?
# Clearly both d and n/d are in the range [1, n], so an upper bound is 2n.
# 
# We can do better; in fact the maximum value of f(d) is n + 1, achieved at the endpoints d = {1, n}.
# The interior values of d yield a smaller value of f(d) because the derivative f'(d) = 1 - n/d^2
# is negative on the interval (1, sqrt(d)) and positive on the interval (sqrt(d), n). Hence on
# the domain [1, n], the function f(d) has a minimum at d = sqrt(n) which is 2*sqrt(n).
# 
# As a sanity check, will 2*sqrt(n) ever be greater than n + 1?
#   (n - 1)^2 >= 0      (because it's a square) (true for all n in reals)
#   n^2 - 2n + 1 >= 0   (expand the multiplication)
#   n^2 + 2n + 1 >= 4n  (add 4n to both sides)
#   (n + 1)^2 >= 4n     (factor)
#   n + 1 >= 2*sqrt(n)  (take square root on both sides, valid for n >= 0)
# Therefore for n >= 1, it is true that for all d in [1, n], we have f(d) <= n + 1.
# 
# Thus we go ahead and precompute a table of primeness for the domain [1, 10^8 + 1],
# and it will be valid for f(d) = d + n/d for any n and d in the appropriate ranges.
# 
# Now we discuss how to test whether an integer n is "prime-generating" or not.
# Every integer n trivially has 1 as a factor. As an optimization, we test whether
# f(1) = 1 + n/1 = 1 + n is prime or not. If it isn't prime then we skip this n.
# If it is prime, then we only need to examine all of n's factors in the range (1, sqrt(n)].
# This is because every factor in the remaining upper range of (sqrt(n), n] can be computed by
# n/d for values of d in the lower range [1, sqrt(n)]. But in this problem, we don't even
# need to look at these complementary upper factors because f(d) has the same value as f(n/d).
def compute():
	LIMIT = 10**8
	
	isprime = eulerlib.list_primality(LIMIT + 1)
	
	def is_prime_generating(n):
		return all(
			(n % d != 0 or isprime[d + n // d])
			for d in range(2, eulerlib.sqrt(n) + 1))
	
	ans = sum(n for n in range(LIMIT + 1)
		if isprime[n + 1] and is_prime_generating(n))
	return str(ans)


if __name__ == "__main__":
	print(compute())
