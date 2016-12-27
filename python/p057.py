# 
# Solution to Project Euler problem 57
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = 0
	numer = 0
	denom = 1
	for i in range(1000):
		numer, denom = denom, denom * 2 + numer
		if len(str(numer + denom)) > len(str(denom)):
			ans += 1
	return str(ans)


if __name__ == "__main__":
	print(compute())
