# 
# Solution to Project Euler problem 57
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	LIMIT = 1000
	ans = 0
	numer = 0
	denom = 1
	for _ in range(LIMIT):
		numer, denom = denom, denom * 2 + numer
		# Now numer/denom is the i'th (0-based) continued fraction approximation of sqrt(2) - 1
		if len(str(numer + denom)) > len(str(denom)):
			ans += 1
	return str(ans)


if __name__ == "__main__":
	print(compute())
