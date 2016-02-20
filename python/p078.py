# 
# Solution to Project Euler problem 78
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


MODULUS = 10**6

def compute():
	for i in itertools.count():
		ans = search(1 << i)
		if ans is not None:
			return str(ans)


def search(limit):
	# partitions[i] is {the number of ways i can be written
	# as an unordered sum of positive integers} mod 10^6.
	# Note that the partition function P(n, k) can be computed with
	# dynamic programming using only 1 dimension for memoization.
	partitions = [0] * limit
	partitions[0] = 1
	for i in range(1, limit):
		for j in range(i, limit):
			partitions[j] = (partitions[j] + partitions[j - i]) % MODULUS
	for (i, val) in enumerate(partitions):
		if val == 0:
			return i
	return None


if __name__ == "__main__":
	print(compute())
