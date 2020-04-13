# 
# Solution to Project Euler problem 150
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	# Generate the triangle
	ROWS = 1000
	rand = lcg_random()
	triangle = [[next(rand) for j in range(i + 1)] for i in range(ROWS)]
	try:
		ans = compute_numpy(triangle)
	except ImportError:
		ans = compute_plain(triangle)
	return str(ans)


def compute_plain(triangle):
	# Calculate cumulative sums for each row
	rowsums = []
	for row in triangle:
		rowsum = [0]
		for j in range(len(row)):
			rowsum.append(rowsum[j] + row[j])
		rowsums.append(rowsum)
	
	# Calculate minimum subtriangle sum for each apex position
	result = 0
	for i in range(len(triangle)):
		for j in range(len(triangle[i])):
			# Apex element selected at triangle[i][j]
			cursum = 0
			for k in range(i, len(triangle)):  # Ending row (inclusive)
				cursum += rowsums[k][k - i + 1 + j] - rowsums[k][j]
				result = min(cursum, result)
	return result


def compute_numpy(triangle):
	# Calculate cumulative sums for each row
	import numpy
	ROWS = len(triangle)
	rowsums = numpy.zeros([ROWS, ROWS + 2], dtype=numpy.int64)
	for (i, row) in enumerate(triangle):
		rowsums[i, : i + 2] = numpy.cumsum([0] + row, dtype=numpy.int64)
	
	# Calculate minimum subtriangle sum for each apex position
	result = 0
	for i in range(len(triangle)):
		for j in range(len(triangle[i])):
			# Apex element selected at triangle[i][j]
			ks = numpy.arange(i, ROWS, dtype=numpy.uint32)
			terms = rowsums[ks, ks - i + 1 + j] - rowsums[ks, j]
			sums = numpy.cumsum(terms, dtype=numpy.int64)
			result = min(numpy.min(sums), result)
	return result


def lcg_random():
	state = 0
	while True:
		state = (615949 * state + 797807) & ((1 << 20) - 1)
		yield state - (1 << 19)


if __name__ == "__main__":
	print(compute())
