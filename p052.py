# 
# Solution to Project Euler problem 52
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	for i in itertools.count(1):
		digits = list(sorted(str(i)))
		for j in range(2, 7):
			temp = list(sorted(str(i * j)))
			if temp != digits:
				break
		else:
			return str(i)


if __name__ == "__main__":
	print(compute())
