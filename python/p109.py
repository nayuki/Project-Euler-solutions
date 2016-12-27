# 
# Solution to Project Euler problem 109
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	# Both lists are orderless but duplicates are important; they are sort of like multisets
	points = [i * j for i in range(1, 21) for j in range(1, 4)] + [25, 50]
	doublepoints = [i * 2 for i in range(1, 21)] + [25 * 2]
	
	# Memoization array, with dimensions (3, 101, len(points))
	ways = [[[None] * len(points) for j in range(101)] for i in range(3)]
	
	# Number of ways to get exactly 'total' points in exactly 'throwz' throws, using
	# items (unordered) from the 'points' list with index less than or equal to 'maxIndex'.
	def calc_ways(throws, total, maxindex):
		if ways[throws][total][maxindex] is None:
			if throws == 0:
				result = 1 if total == 0 else 0
			else:
				result = 0
				if maxindex > 0:
					result += calc_ways(throws, total, maxindex - 1)
				if points[maxindex] <= total:
					result += calc_ways(throws - 1, total - points[maxindex], maxindex)
			ways[throws][total][maxindex] = result
		return ways[throws][total][maxindex]
	
	checkouts = 0
	for remainingpoints in range(1, 100):
		for throws in range(3):
			for p in doublepoints:
				if p <= remainingpoints:
					checkouts += calc_ways(throws, remainingpoints - p, len(points) - 1)
	return str(checkouts)


if __name__ == "__main__":
	print(compute())
