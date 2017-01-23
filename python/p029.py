# 
# Solution to Project Euler problem 29
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# We generate all the possible powers in the given range, put each value
# into a set, and let the set count the number of unique values present.
def compute():
	seen = set(a**b for a in range(2, 101) for b in range(2, 101))
	return str(len(seen))


if __name__ == "__main__":
	print(compute())
