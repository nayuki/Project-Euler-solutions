# 
# Solution to Project Euler problem 24
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


# We initialize a list as the lowest permutation of the given digits, which is the sequence
# (0,1,2,3,4,5,6,7,8,9). Then we call a Python library function that generates a stream of
# all permutations of the values, seek to the 999 999th element (0-based indexing), and stringify it.
def compute():
	arr = list(range(10))
	temp = itertools.islice(itertools.permutations(arr), 999999, None)
	return "".join(str(x) for x in next(temp))


if __name__ == "__main__":
	print(compute())
