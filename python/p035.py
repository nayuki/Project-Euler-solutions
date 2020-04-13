# 
# Solution to Project Euler problem 35
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	isprime = eulerlib.list_primality(999999)
	def is_circular_prime(n):
		s = str(n)
		return all(isprime[int(s[i : ] + s[ : i])] for i in range(len(s)))
	
	ans = sum(1
		for i in range(len(isprime))
		if is_circular_prime(i))
	return str(ans)


if __name__ == "__main__":
	print(compute())
