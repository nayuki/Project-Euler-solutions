# 
# Solution to Project Euler problem 62
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	numdigits = 0
	lowest = {}
	counts = {}
	for i in itertools.count():
		numclass = get_cube_number_class(i)
		
		if len(numclass) > numdigits:
			# Process and flush data for smaller number of digits
			min = None
			for nc in counts:
				if counts[nc] == 5 and (min is None or lowest[nc] < min):
					min = lowest[nc]
			if min is not None:
				return str(min**3)
			lowest = {}
			counts = {}
			numdigits = len(numclass)
		
		if numclass not in lowest:
			lowest[numclass] = i
			counts[numclass] = 0
		counts[numclass] += 1


def get_cube_number_class(n):
	digits = [int(c) for c in str(n**3)]
	digits.sort()
	return "".join(str(d) for d in digits)


if __name__ == "__main__":
	print(compute())
