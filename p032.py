# 
# Solution to Project Euler problem 32
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


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


def has_pandigital_product(n):
	# Find and examine all factors of n
	for i in range(1, sqrt(n) + 1):
		if n % i == 0:
			temp = str(n) + str(i) + str(n // i)
			if "".join(sorted(temp)) == "123456789":
				return True
	return False


# For contradiction suppose a candidate (x, y, z) has z >= 10000.
# Then x*y consumes at least 5 digits. With the 4 (or fewer) remaining digits, even the
# upper bound of x=99 and y=99 produces a product of x*y < 10000, which is unequal to z.
# Therefore we need the product z < 10000 to be able to find possible x and y values.
ans = 0
for i in range(1, 10000):
	if has_pandigital_product(i):
		ans += i
print(ans)
