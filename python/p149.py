# 
# Solution to Project Euler problem 149
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	SIZE = 2000
	
	# Generate the pseudorandom sequence according to the lagged Fibonacci generator
	randseq = []
	for i in range(SIZE**2):
		k = i + 1
		if k <= 55:
			randseq.append((100003 - 200003*k + 300007*k*k*k) % 1000000 - 500000)
		else:
			randseq.append((randseq[-24] + randseq[-55]) % 1000000 - 500000)
	
	# Reshape the sequence into into a 2D array
	grid = [randseq[i * SIZE : (i + 1) * SIZE] for i in range(SIZE)]
	
	def get_max_substring_sum(x, y, dx, dy):
		lst = []
		while 0 <= x < SIZE and 0 <= y < SIZE:
			lst.append(grid[y][x])
			x += dx
			y += dy
		result = 0
		current = 0
		for item in lst:
			current = max(current + item, 0)
			result = max(current, result)
		return result
	
	maximum = 0
	for i in range(SIZE):
		maximum = max(maximum,
			# Top edge
			get_max_substring_sum(i, 0,  0, +1),  # Vertical
			get_max_substring_sum(i, 0, +1, +1),  # Diagonal
			get_max_substring_sum(i, 0, -1, +1),  # Anti-diagonal
			# Left edge
			get_max_substring_sum(0, i,  0, +1),  # Horizontal
			get_max_substring_sum(0, i, +1, +1),  # Diagonal
			# Right edge
			get_max_substring_sum(SIZE - 1, i,  0, +1),  # Horizontal
			get_max_substring_sum(SIZE - 1, i, -1, +1))  # Anti-diagonal
	return str(maximum)


if __name__ == "__main__":
	print(compute())
