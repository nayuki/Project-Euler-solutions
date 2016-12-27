# 
# Solution to Project Euler problem 23
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	LIMIT = 28124
	divisorsum = [0] * LIMIT
	for i in range(1, LIMIT):
		for j in range(i * 2, LIMIT, i):
			divisorsum[j] += i
	abundantnums = [i for (i, x) in enumerate(divisorsum) if x > i]
	
	expressible = [False] * LIMIT
	for i in abundantnums:
		for j in abundantnums:
			if i + j < LIMIT:
				expressible[i + j] = True
			else:
				break
	
	ans = sum(i for (i, x) in enumerate(expressible) if not x)
	return str(ans)


if __name__ == "__main__":
	print(compute())
