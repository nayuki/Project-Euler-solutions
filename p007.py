# 
# Solution to Project Euler problem 7
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	n = 2
	count = 0
	while True:
		if is_prime(n):
			count += 1
			if count == 10001:
				break
		n += 1
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


def is_prime(x):
	if x <= 1:
		return False
	for i in range(2, sqrt(x) + 1):
		if x % i == 0:
			return False
	return True


if __name__ == "__main__":
	print(compute())
