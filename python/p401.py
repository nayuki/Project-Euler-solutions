# 
# Solution to Project Euler problem 401
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# Consider the set of all integers from 1 to n, inclusive: {1, 2, ..., n}.
# Now form the set of divisors for each number:
#   1: {1}
#   2: {1, 2}
#   3: {1, 3}
#   4: {1, 2, 4}
#   5: {1, 5}
#   6: {1, 2, 3, 6}
#   et cetera until n.
# Next consider the multiset union of all these sets of divisors.
# 
# We know that for a given integer k > 0, it occurs as a divisor in this multiset
# exactly floor(n / k) times (we call this the "count"), which are namely the multiples of k.
# So instead of considering each integer and summing its squared divisors, we can consider
# each divisor from 1 to n and compute how much it contributes to the final sum, namely floor(n / k) * k^2.
# 
# A further observation is that when k is large, the count factor of floor(n / k) does not change often.
# (For example, for k from floor(n/2)+1 to n, this count is always 1.)
# So we can calculate the squared divisor sum for many numbers at a time.
# This is helpful for k > sqrt(n), and we can bring the run time from O(n) down to O(sqrt(n)).
# 
# For a given count of m = floor(n / k), which integer values of k yield this m?
# By the definition of floor, m <= n/k, so mk <= n, and k <= n/m, thus k <= floor(n/m).
# Also by definition, m > n/k - 1, so mk > n - k, and k(m + 1) > n, and k > n/(m+1), so k > floor(n/(m+1)).
# Together, we have: floor(n / (m + 1)) < k <= floor(n / m).
# 
# Useful fact: (sum k^2 for k=1 to n) = n(n + 1)(2n + 1) / 6.
def compute():
	LIMIT = 10**15
	MODULUS = 10**9
	
	# Can be any number from 1 to LIMIT, but somewhere near sqrt(LIMIT) is preferred
	splitcount = eulerlib.sqrt(LIMIT)
	# Consider divisors individually up and including this number
	splitat = LIMIT // (splitcount + 1)
	
	# The sum (s+1)^2 + (s+2)^2 + ... + (e-1)^2 + e^2.
	def sum_squares(s, e):
		return (e * (e + 1) * (e * 2 + 1) - s * (s + 1) * (s * 2 + 1)) // 6
	
	ans = sum((i * i * (LIMIT // i)) for i in range(1, splitat + 1))
	ans += sum((sum_squares(LIMIT // (i + 1), LIMIT // i) * i) for i in range(1, splitcount + 1))
	return str(ans % MODULUS)


if __name__ == "__main__":
	print(compute())
