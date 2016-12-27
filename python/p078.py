# 
# Solution to Project Euler problem 78
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


MODULUS = 10**6

def compute():
	partitions = [1]
	for i in itertools.count(len(partitions)):
		# We calculate partitions[i] mod 10^6 using a formula based on generalized pentagonal numbers:
		#   partitions(i) =   partitions(i - pentagonal(1)) + partitions(i - pentagonal(-1))
		#                   - partitions(i - pentagonal(2)) - partitions(i - pentagonal(-2))
		#                   + partitions(i - pentagonal(3)) + partitions(i - pentagonal(-3))
		#                   - partitions(i - pentagonal(4)) - partitions(i - pentagonal(-4))
		#                   + ...,
		#   where pentagonal(j) = (3*n^2 - n) / 2, and
		#   we stop the sum when i - pentagonal(+/-j) < 0.
		# Note that for j > 0, pentagonal(j) < pentagonal(-j) < pentagonal(j+1).
		# 
		# (The formula is used without mathematical justification;
		# see https://en.wikipedia.org/wiki/Partition_(number_theory)#Generating_function .)
		item = 0
		for j in itertools.count(1):
			sign = -1 if j % 2 == 0 else +1
			index = (j * j * 3 - j) // 2
			if index > i:
				break
			item += partitions[i - index] * sign
			index += j  # index == (j * j * 3 + j) // 2
			if index > i:
				break
			item += partitions[i - index] * sign
			item %= MODULUS
		
		# Check or memoize the number
		if item == 0:
			return str(i)
		partitions.append(item)


if __name__ == "__main__":
	print(compute())
