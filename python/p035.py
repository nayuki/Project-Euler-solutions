# 
# Solution to Project Euler problem 35
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, sys
if sys.version_info.major == 2:
	range = xrange


isprime = eulerlib.list_primality(999999)

def compute():
	ans = sum(1
		for i in range(len(isprime))
		if is_circular_prime(i))
	return str(ans)


def is_circular_prime(n):
	s = str(n)
	return all(isprime[int(s[i : ] + s[ : i])] for i in range(len(s)))


if __name__ == "__main__":
	print(compute())
