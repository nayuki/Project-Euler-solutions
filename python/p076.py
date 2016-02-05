# 
# Solution to Project Euler problem 76
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	LIMIT = 100
	partitions = []
	for i in range(LIMIT + 1):
		partitions.append([None] * (LIMIT + 1))
		for j in reversed(range(0, LIMIT + 1)):
			if j == i:
				partitions[i][j] = 1
			elif j > i:
				partitions[i][j] = 0
			elif j == 0:
				partitions[i][j] = partitions[i][j + 1]
			else:
				partitions[i][j] = partitions[i][j + 1] + partitions[i - j][j]
	
	ans = partitions[LIMIT][1] - 1
	return str(ans)


if __name__ == "__main__":
	print(compute())
