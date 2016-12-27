# 
# Solution to Project Euler problem 61
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	# Build table of numbers
	# numbers[i][j] is the set of figurate numbers of i sides (3 <= i <= 8), having 4 digits, beginning with the 2 digits equal to j
	numbers = [[set() for j in range(100)] for i in range(9)]
	for sides in range(3, 9):
		for n in itertools.count(1):
			num = figurate_number(sides, n)
			if num >= 10000:
				break
			if num >= 1000:
				numbers[sides][num // 100].add(num)
	
	# Note: sidesused is a bit set
	def find_solution_sum(begin, current, sidesused, sum):
		if sidesused == 0b111111000:
			if current % 100 == begin // 100:
				return sum
		else:
			for sides in range(4, 9):
				if (sidesused >> sides) & 1 != 0:
					continue
				for num in numbers[sides][current % 100]:
					temp = find_solution_sum(begin, num, sidesused | (1 << sides), sum + num)
					if temp is not None:
						return temp
			return None
	
	# Do search
	for i in range(10, 100):
		for num in numbers[3][i]:
			temp = find_solution_sum(num, num, 1 << 3, num)
			if temp is not None:
				return str(temp)
	raise AssertionError("No solution")


def figurate_number(sides, n):
	return n * ((sides - 2) * n - (sides - 4)) // 2


if __name__ == "__main__":
	print(compute())
