# 
# Solution to Project Euler problem 40
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	s = "".join(str(i) for i in range(1, 1000000))
	ans = 1
	for i in range(7):
		ans *= int(s[10**i - 1])
	return str(ans)


if __name__ == "__main__":
	print(compute())
