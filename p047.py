# 
# Solution to Project Euler problem 47
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	for i in itertools.count():
		for j in range(4):
			if count_distinct_prime_factors(i + j) != 4:
				break
		else:
			return str(i)


distinctprimefactorscache = {}

def count_distinct_prime_factors(n):
	if n not in distinctprimefactorscache:
		count = 0
		while n > 1:
			count += 1
			k = smallest_prime_factor(n)
			n //= k
			while n % k == 0:
				n //= k
		distinctprimefactorscache[n] = count
	return distinctprimefactorscache[n]


def smallest_prime_factor(x):
	for i in range(2, eulerlib.sqrt(x) + 1):
		if x % i == 0:
			return i
	return x


if __name__ == "__main__":
	print(compute())
