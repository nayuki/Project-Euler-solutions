# 
# Solution to Project Euler problem 150
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import sys
if sys.version_info.major == 2:
	range = xrange


def compute():
	# Generate the triangle
	ROWS = 1000
	rand = lcg_random()
	triangle = [[next(rand) for j in range(i + 1)] for i in range(ROWS)]
	
	# Calculate cumulative sums for each row
	rowsums = []
	for row in triangle:
		rowsum = [0]
		for j in range(len(row)):
			rowsum.append(rowsum[j] + row[j])
		rowsums.append(rowsum)
	
	# Calculate minimum subtriangle sum for each apex position
	ans = 0
	for i in range(len(triangle)):
		for j in range(len(triangle[i])):
			# Apex element selected at triangle[i][j]
			cursum = 0
			for k in range(i, len(triangle)):  # Ending row (inclusive)
				cursum += rowsums[k][k - i + 1 + j] - rowsums[k][j]
				ans = min(cursum, ans)
	return str(ans)


def lcg_random():
	state = 0
	while True:
		state = (615949 * state + 797807) & ((1 << 20) - 1)
		yield state - (1 << 19)


if __name__ == "__main__":
	print(compute())
