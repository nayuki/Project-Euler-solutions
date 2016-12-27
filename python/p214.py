# 
# Solution to Project Euler problem 214
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import array


# Requires about 400 MB of memory
def compute():
	LIMIT = 40000000
	totient = list_totients(LIMIT - 1)
	totientchainlen = array.array("L", [0, 1])
	ans = 0
	# Fill table in ascending order because totient chains are strictly decreasing
	for i in range(len(totientchainlen), len(totient)):
		chainlen = totientchainlen[totient[i]] + 1
		totientchainlen.append(chainlen)
		if chainlen == 25 and totient[i] == i - 1:  # i is prime iff totient(i) = i-1
			ans += i
	return str(ans)


def list_totients(n):
	assert n < (1 << 32)
	result = array.array("L", range(n + 1))
	for i in range(2, n + 1):
		if result[i] == i:  # i is prime
			for j in range(i, n + 1, i):
				result[j] = result[j] // i * (i - 1)
	return result


if __name__ == "__main__":
	print(compute())
