# 
# Solution to Project Euler problem 34
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	# As stated in the problem, 1 = 1! and 2 = 2! are excluded.
	# If a number has at least n >= 8 digits, then even if every digit is 9,
	# n * 9! is still less than the number (which is at least 10^n).
	ans = 0
	for i in range(3, 10000000):
		if i == factorial_digit_sum(i):
			ans += i
	return str(ans)


FACTORIAL = list(map(eulerlib.factorial, range(10)))

def factorial_digit_sum(n):
	return sum([FACTORIAL[int(c)] for c in str(n)])


if __name__ == "__main__":
	print(compute())
