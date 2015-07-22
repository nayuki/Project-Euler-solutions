# 
# Solution to Project Euler problem 3
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	n = 600851475143
	while True:
		k = smallest_prime_factor(n)
		if k != n:
			n //= k
		else:
			return str(n)


def smallest_prime_factor(x):
	for i in range(2, eulerlib.sqrt(x) + 1):
		if x % i == 0:
			return i
	return x


if __name__ == "__main__":
	print(compute())
