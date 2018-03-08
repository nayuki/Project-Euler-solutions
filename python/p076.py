# 
# Solution to Project Euler problem 76
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	LIMIT = 100
	partitions = []
	for i in range(LIMIT + 1):
		partitions.append([None] * (LIMIT + 1))
		for j in reversed(range(LIMIT + 1)):
			if j == i:
				val = 1
			elif j > i:
				val = 0
			elif j == 0:
				val = partitions[i][j + 1]
			else:
				val = partitions[i][j + 1] + partitions[i - j][j]
			partitions[i][j] = val
	
	ans = partitions[LIMIT][1] - 1
	return str(ans)


if __name__ == "__main__":
	print(compute())
