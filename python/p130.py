# 
# Solution to Project Euler problem 130
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	cond = lambda i: (i % 5 != 0) and (not eulerlib.is_prime(i)) \
		and ((i - 1) % find_least_divisible_repunit(i) == 0)
	ans = sum(itertools.islice(filter(cond, itertools.count(7, 2)), 25))
	return str(ans)


# Returns the smallest k such that R(k) is divisible by n.
def find_least_divisible_repunit(n):
	if n % 2 == 0 or n % 5 == 0:
		return 0
	sum = 1  # Equal to R(k) mod n
	pow = 1  # Equal to 10^k mod n
	k = 1
	while sum % n != 0:
		k += 1
		pow = pow * 10 % n
		sum = (sum + pow) % n
	return k


if __name__ == "__main__":
	print(compute())
