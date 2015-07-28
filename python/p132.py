# 
# Solution to Project Euler problem 132
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	sum = 0
	count = 0
	i = 2
	while count < 40:
		if eulerlib.is_prime(i) and repunit_mod(10**9, i) == 0:
			sum += i
			count += 1
		i += 1
	return str(sum)


def repunit_mod(k, m):
	return (pow(10, k, m * 9) - 1) // 9


if __name__ == "__main__":
	print(compute())
