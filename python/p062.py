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
	data = {}  # str numclass -> (int lowest, int count)
	for i in itertools.count():
		digits = [int(c) for c in str(i**3)]
		digits.sort()
		numclass = "".join(str(d) for d in digits)
		
		if len(numclass) > numdigits:
			# Process and flush data for smaller number of digits
			min = None
			for (nc, vals) in data.items():
				if vals[1] == 5 and (min is None or vals[0] < min):
					min = vals[0]
			if min is not None:
				return str(min**3)
			data = {}
			numdigits = len(numclass)
		
		lowest, count = data.get(numclass, (i, 0))
		data[numclass] = (lowest, count + 1)


if __name__ == "__main__":
	print(compute())
