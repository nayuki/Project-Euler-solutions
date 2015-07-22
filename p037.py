# 
# Solution to Project Euler problem 37
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = 0
	found = 0
	n = 10
	while found < 11:
		if is_truncatable_prime(n):
			ans += n
			found += 1
		n += 1
	return str(ans)


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
	if x < 2:
		return False
	for i in range(2, sqrt(x) + 1):
		if x % i == 0:
			return False
	return True


def is_truncatable_prime(n):
	# Test if left-truncatable
	i = 10
	while i <= n:
		if not is_prime(n % i):
			return False
		i *= 10
	
	# Test if right-truncatable
	x = n
	while x > 0:
		if not is_prime(x):
			return False
		x //= 10
	return True


if __name__ == "__main__":
	print(compute())
