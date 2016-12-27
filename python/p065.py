# 
# Solution to Project Euler problem 65
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	numer = 1
	denom = 0
	for i in reversed(range(100)):
		numer, denom = e_contfrac_term(i) * numer + denom, numer
	ans = sum(int(c) for c in str(numer))
	return str(ans)


def e_contfrac_term(i):
	if i == 0:
		return 2
	elif i % 3 == 2:
		return i // 3 * 2 + 2
	else:
		return 1


if __name__ == "__main__":
	print(compute())
