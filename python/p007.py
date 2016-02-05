# 
# Solution to Project Euler problem 7
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	n = 1
	count = 0
	while count < 10001:
		n += 1
		if eulerlib.is_prime(n):
			count += 1
	return str(n)


if __name__ == "__main__":
	print(compute())
