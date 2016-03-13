# 
# Solution to Project Euler problem 12
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	triangle = 0
	for i in itertools.count(1):
		triangle += i  # This is the ith triangle number, i.e. num = 1 + 2 + ... + i = i * (i + 1) / 2
		if num_divisors(triangle) > 500:
			break
	return str(triangle)


# Returns the number of integers in the range [1, n] that divide n.
def num_divisors(n):
	result = 0
	end = eulerlib.sqrt(n)
	for i in range(1, end + 1):
		if n % i == 0:
			result += 2
	if end**2 == n:
		result -= 1
	return result


if __name__ == "__main__":
	print(compute())
