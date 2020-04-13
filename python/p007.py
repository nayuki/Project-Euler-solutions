# 
# Solution to Project Euler problem 7
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


# Computers are fast, so we can implement this solution by testing each number
# individually for primeness, instead of using the more efficient sieve of Eratosthenes.
# 
# The algorithm starts with an infinite stream of incrementing integers starting at 2,
# filters them to keep only the prime numbers, drops the first 10000 items,
# and finally returns the first item thereafter.
def compute():
	ans = next(itertools.islice(filter(eulerlib.is_prime, itertools.count(2)), 10000, None))
	return str(ans)


if __name__ == "__main__":
	print(compute())
