# 
# Solution to Project Euler problem 191
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	NUM_DAYS = 30
	MAX_ABSENT = 2
	MAX_LATE = 1
	
	# num_prize_strings[i][j][k] is the number of prize strings of length i with
	# exactly j absences at the tail and exactly k lates in the whole string
	num_prize_strings = create_nested_lists(0, NUM_DAYS + 1, MAX_ABSENT + 1, MAX_LATE + 1)
	num_prize_strings[0][0][0] = 1
	for i in range(1, len(num_prize_strings)):
		for j in range(len(num_prize_strings[i])):
			for k in range(len(num_prize_strings[i][j])):
				if j == 0:
					s = 0
					for l in range(MAX_ABSENT + 1):
						s += num_prize_strings[i - 1][l][k]  # On time
					if k > 0:
						for l in range(MAX_ABSENT + 1):
							s += num_prize_strings[i - 1][l][k - 1]  # Late
				else:
					s = num_prize_strings[i - 1][j - 1][k]  # Absent
				num_prize_strings[i][j][k] = s
	
	ans = sum(map(sum, num_prize_strings[NUM_DAYS]))
	return str(ans)


def create_nested_lists(value, *dimensions):
	if len(dimensions) == 0:
		raise ValueError()
	elif len(dimensions) == 1:
		return [value] * dimensions[0]
	else:
		return [create_nested_lists(value, *dimensions[1 : ]) for _ in range(dimensions[0])]


if __name__ == "__main__":
	print(compute())
