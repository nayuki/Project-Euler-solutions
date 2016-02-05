# 
# Solution to Project Euler problem 132
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	ans = 0
	count = 0
	for i in itertools.count(2):
		if eulerlib.is_prime(i) and repunit_mod(10**9, i) == 0:
			ans += i
			count += 1
			if count == 40:
				return str(ans)


def repunit_mod(k, m):
	return (pow(10, k, m * 9) - 1) // 9


if __name__ == "__main__":
	print(compute())
