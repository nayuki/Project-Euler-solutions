# 
# Solution to Project Euler problem 3
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	n = 600851475143
	while True:
		k = smallest_prime_factor(n)
		if k != n:
			n //= k
		else:
			break
	return str(n)


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


def smallest_prime_factor(x):
	for i in range(2, sqrt(x) + 1):
		if x % i == 0:
			return i
	return x


if __name__ == "__main__":
	print(compute())
