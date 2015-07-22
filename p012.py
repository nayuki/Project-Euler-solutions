# 
# Solution to Project Euler problem 12
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	i = 1
	triangular = 0
	while True:
		triangular += i
		if num_divisors(triangular) > 500:
			break
		i += 1
	return str(triangular)


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


def num_divisors(x):
	result = 0
	k = sqrt(x)
	for i in range(1, k + 1):
		if x % i == 0:
			result += 2
	if k**2 == x:
		result -= 1
	return result


if __name__ == "__main__":
	print(compute())
