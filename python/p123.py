# 
# Solution to Project Euler problem 123
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# Using the result from Project Euler #120, we know that
# (a-1)^n + (a+1)^n mod a^2 = if (n is even) then 2 else 2an.
# Since 2 is tiny, we can skip the n is even case.
# a is the n'th (1-based) prime number, so a > n. In fact for n >= 5,
# we have a > 2n, so we can take 2an directly without moduloing it by a^2.
def compute():
	primes = eulerlib.list_primes(1000000)
	for n in range(5, len(primes), 2):
		rem = n * primes[n - 1] * 2
		if rem > 10000000000:
			return str(n)


if __name__ == "__main__":
	print(compute())
