# 
# Solution to Project Euler problem 178
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, functools


def compute():
	LIMIT = 40
	ans = sum(count_step_pandigital(digits, head, 0, 9)
		for digits in range(LIMIT + 1)
		for head in range(1, 10))
	return str(ans)


# Returns the number of pandigital step numbers such that each
# number is 'digits' long, starts with the digit 'head' (can be 0),
# and the union of all the digits equals the interval [low, high].
@functools.cache
def count_step_pandigital(digits, head, low, high):
	assert digits >= 0
	assert low <= head <= high
	if digits <= 1:
		return 1 if (low == head == high) else 0
	else:
		result = 0
		if head - 1 >= low:
			result += count_step_pandigital(digits - 1, head - 1, low, high)
			if head == high:
				result += count_step_pandigital(digits - 1, head - 1, low, high - 1)
		if head + 1 <= high:
			result += count_step_pandigital(digits - 1, head + 1, low, high)
			if head == low:
				result += count_step_pandigital(digits - 1, head + 1, low + 1, high)
		assert 0 <= result < 10**digits
		return result


if __name__ == "__main__":
	print(compute())
