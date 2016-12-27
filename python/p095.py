# 
# Solution to Project Euler problem 95
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	LIMIT = 10**6
	
	# divisorsum[n] is the sum of all the proper divisors of n
	divisorsum = [0] * (LIMIT + 1)
	for i in range(1, LIMIT + 1):
		for j in range(i * 2, LIMIT + 1, i):
			divisorsum[j] += i
	
	# Analyze the amicable chain length for each number in ascending order
	maxchainlen = 0
	ans = -1
	for i in range(LIMIT + 1):
		visited = set()
		cur = i
		for count in itertools.count(1):
			# 'count' is the length of the this amicable chain
			visited.add(cur)
			next = divisorsum[cur]
			if next == i:
				if count > maxchainlen:
					ans = i
					maxchainlen = count
				break
			# Exceeds limit or not a chain (a rho shape instead)
			elif next > LIMIT or next in visited:
				break
			else:
				cur = next
	
	return str(ans)


if __name__ == "__main__":
	print(compute())
