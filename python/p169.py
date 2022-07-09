# 
# Solution to Project Euler problem 169
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, functools, sys


def compute():
	sys.setrecursionlimit(3000)
	NUMBER = 10**25
	ans = count_ways(NUMBER, NUMBER.bit_length() - 1, 2)
	return str(ans)


@functools.cache
def count_ways(number, exponent, repetitions):
	if exponent < 0:
		return 1 if number == 0 else 0
	else:
		result = count_ways(number, exponent - 1, 2)
		power = 1 << exponent
		upper = power * (repetitions + 2)
		if repetitions > 0 and power <= number < upper:
			result += count_ways(number - power, exponent, repetitions - 1)
		return result


if __name__ == "__main__":
	print(compute())
