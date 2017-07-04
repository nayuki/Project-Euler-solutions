# 
# Solution to Project Euler problem 425
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, heapq


# Finding all the relatives of 2 can be seen as a single-source shortest path problem,
# which we solve here using Dijkstra's algorithm. The key insight is that at each node (prime number),
# we consider the connection path from 2 to it, and store the maximum path number at the node.
# It is amenable to dynamic programming because it's always best to minimize the maximum path number.
# 
# For example, 2 is connected to 103 because 2 <-> 3 <-> 13 <-> 113 <-> 103.
# The maximum number along this path is 113, and among all paths
# this is the minimum possible maximum, so 103 is not a relative of 2.
def compute():
	LIMIT = 10**7
	
	isprime = eulerlib.list_primality(LIMIT)
	
	# pathmax[i] = None if i is not prime or i is not connected to 2.
	# Otherwise, considering all connection paths from 2 to i and for each path computing
	# the maximum number, pathmax[i] is the minimum number among all these maxima.
	pathmax = [None] * len(isprime)
	
	# Process paths in increasing order of maximum number
	queue = [(2, 2)]
	while len(queue) > 0:
		pmax, n = heapq.heappop(queue)
		if pathmax[n] is not None and pmax >= pathmax[n]:
			# This happens if at the time this update was queued, a better
			# or equally good update was queued ahead but not processed yet
			continue
		
		# Update the target node and explore neighbors
		pathmax[n] = pmax
		
		# Try all replacements of a single digit, including the leading zero.
		# This generates exactly all (no more, no less) the ways that a number m is connected to n.
		digits = to_digits(n)
		tempdigits = list(digits)
		for i in range(len(tempdigits)):  # For each digit position
			for j in range(10):  # For each digit value
				tempdigits[i] = j
				m = to_number(tempdigits)
				nextpmax = max(m, pmax)
				if m < len(isprime) and isprime[m] and (pathmax[m] is None or nextpmax < pathmax[m]):
					heapq.heappush(queue, (nextpmax, m))
			tempdigits[i] = digits[i]  # Restore the digit
	
	ans = sum(i for i in range(len(isprime))
		if isprime[i] and (pathmax[i] is None or pathmax[i] > i))
	return str(ans)


# Returns the given non-negative integer as an array of digits, in big endian, with an extra leading zero.
# e.g. 0 -> [0,0]; 1 -> [0,1]; 8 -> [0,8]; 42 -> [0,4,2]; 596 -> [0,5,9,6].
def to_digits(n):
	if n < 0:
		raise ValueError()
	
	# Extract base-10 digits in little endian
	temp = []
	while True:
		temp.append(n % 10)
		n //= 10
		if n == 0:
			break
	
	temp.append(0)
	temp.reverse()
	return temp


def to_number(digits):
	result = 0
	for x in digits:
		result = result * 10 + x
	return result


if __name__ == "__main__":
	print(compute())
