# 
# Solution to Project Euler problem 5
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = 1
	for i in range(1, 21):
		ans *= i // gcd(i, ans)
	return str(ans)


def gcd(x, y):
	while y != 0:
		x, y = y, x % y
	return x


if __name__ == "__main__":
	print(compute())
