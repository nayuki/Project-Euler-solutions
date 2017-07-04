# 
# Solution to Project Euler problem 55
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = sum(1 for i in range(10000) if is_lychrel(i))
	return str(ans)


def is_lychrel(n):
	for i in range(50):
		n += int(str(n)[ : : -1])
		if str(n) == str(n)[ : : -1]:
			return False
	return True


if __name__ == "__main__":
	print(compute())
