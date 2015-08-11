# 
# Solution to Project Euler problem 10
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	ans = sum(eulerlib.list_primes(1999999))
	return str(ans)


if __name__ == "__main__":
	print(compute())
