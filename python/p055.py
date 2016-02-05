# 
# Solution to Project Euler problem 55
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = 0
	for i in range(10000):
		if is_lychrel(i):
			ans += 1
	return str(ans)


def is_lychrel(n):
	for i in range(50):
		n += int(str(n)[::-1])
		if str(n) == str(n)[::-1]:
			return False
	return True


if __name__ == "__main__":
	print(compute())
