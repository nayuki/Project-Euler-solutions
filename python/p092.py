# 
# Solution to Project Euler problem 92
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import sys
if sys.version_info.major == 2:
	range = xrange


def compute():
	ans = 0
	terminals = (1, 89)
	for i in range(1, 10000000):
		while i not in terminals:
			i = square_digit_sum(i)
		if i == 89:
			ans += 1
	return str(ans)


def square_digit_sum(n):
	result = 0
	while n > 0:
		result += SQUARE_DIGITS_SUM[n % 1000]
		n //= 1000
	return result

SQUARE_DIGITS_SUM = [sum(int(c)**2 for c in str(i)) for i in range(1000)]


if __name__ == "__main__":
	print(compute())
