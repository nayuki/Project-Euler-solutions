# 
# Solution to Project Euler problem 357
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	LIMIT = 10**8
	
	# Because the maximum value of (i + n/i) is (n + 1)
	isprime = eulerlib.list_primality(LIMIT + 1)
	
	def is_prime_generating(n):
		return all(
			n % i != 0 or isprime[i + n // i]
			for i in range(1, eulerlib.sqrt(n) + 1))
	
	ans = sum(n for n in range(LIMIT + 1) if is_prime_generating(n))
	return str(ans)


if __name__ == "__main__":
	print(compute())
