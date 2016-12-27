# 
# Solution to Project Euler problem 149
# Copyright (c) Project Nayuki. All rights reserved.
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
	
	# For the sequence of numbers in the grid at positions (x, y), (x+dx, y+dy), (x+2*dx, y+2*dy), ... until the
	# last in-bounds indices, this function returns the maximum sum among all possible substrings of this sequence.
	def get_max_substring_sum(x, y, dx, dy):
		result = 0
		current = 0
		while 0 <= x < SIZE and 0 <= y < SIZE:
			current = max(current + grid[y][x], 0)  # Reset the running sum if it goes negative
			result = max(current, result)  # Keep track of the best seen running sum
			x += dx
			y += dy
		return result
	
	# Scan along all line directions and positions
	ans = max(
		max(get_max_substring_sum(0, i, +1,  0),  # Horizontal from left edge
		    get_max_substring_sum(i, 0,  0, +1),  # Vertical from top edge
		    get_max_substring_sum(0, i, +1, +1),  # Diagonal from left edge
		    get_max_substring_sum(i, 0, +1, +1),  # Diagonal from top edge
		    get_max_substring_sum(i, 0, -1, +1),  # Anti-diagonal from top edge
		    get_max_substring_sum(SIZE - 1, i, -1, +1))  # Anti-diagonal from right edge
		for i in range(SIZE))
	return str(ans)


if __name__ == "__main__":
	print(compute())
