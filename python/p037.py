# 
# Solution to Project Euler problem 37
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	ans = 0
	found = 0
	n = 10
	while found < 11:
		if is_truncatable_prime(n):
			ans += n
			found += 1
		n += 1
	return str(ans)


def is_truncatable_prime(n):
	# Test if left-truncatable
	i = 10
	while i <= n:
		if not eulerlib.is_prime(n % i):
			return False
		i *= 10
	
	# Test if right-truncatable
	while n > 0:
		if not eulerlib.is_prime(n):
			return False
		n //= 10
	return True


if __name__ == "__main__":
	print(compute())
