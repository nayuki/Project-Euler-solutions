# 
# Shared code for solutions to Project Euler problems
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def gcd(x, y):
	while y != 0:
		x, y = y, x % y
	return x


# Given integer x, this returns the integer floor(sqrt(x)).
def sqrt(x):
	i = 1
	while i * i <= x:
		i *= 2
	y = 0
	while i > 0:
		if (y + i)**2 <= x:
			y += i
		i //= 2
	return y


def is_prime(x):
	if x <= 1:
		return False
	for i in range(2, sqrt(x) + 1):
		if x % i == 0:
			return False
	return True


def factorial(n):
	result = 1
	for i in range(1, n + 1):
		result *= i
	return result
