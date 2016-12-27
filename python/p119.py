# 
# Solution to Project Euler problem 119
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


# Candidates have the form n^k, where n >= 2, k >= 2, n^k >= 10, and isDigitSumPower(n^k) == true.
# We also impose n^k < limit. If there are at least 30 candidates under 'limit',
# then the 30th smallest candidate is the answer. Otherwise we raise the limit and search again.
# 
# We only need to try the exponents k until 2^k exceeds the limit.
# We only need to try the bases n until the power of the digit sum is too small to match n^k.
# The power of the digit sum is digitSum(n^k)^k, which is at most (9 * digitLength(n^k))^k.
def compute():
	INDEX = 30  # 1-based
	limit = 1
	while True:
		candidates = set()
		k = 2
		while (1 << k) < limit:
			for n in itertools.count(2):
				pow = n**k
				if pow >= limit and len(str(pow)) * 9 < n:
					break
				if pow >= 10 and is_digit_sum_power(pow):
					candidates.add(pow)
			k += 1
		if len(candidates) >= INDEX:
			return str(sorted(candidates)[INDEX - 1])
		limit <<= 8


def is_digit_sum_power(x):
	digitsum = sum(int(c) for c in str(x))
	if digitsum == 1:  # Powers of 10 are never a power of 1
		return False
	pow = digitsum
	while pow < x:
		pow *= digitsum
	return pow == x


if __name__ == "__main__":
	print(compute())
