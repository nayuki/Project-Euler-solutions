# 
# Solution to Project Euler problem 44
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools, sys
if sys.version_info.major == 2:
	range = xrange


def compute():
	min_d = -1
	# For each upper pentagonal number index, going upward
	for i in itertools.count(2):
		pent_i = pentagonal_number(i)
		# If the next number down is more than a found difference, then conclude searching
		if min_d != -1 and pent_i - pentagonal_number(i - 1) > min_d:
			break
		
		# For each lower pentagonal number index, going downward
		for j in range(i - 1, 0, -1):
			pent_j = pentagonal_number(j)
			diff = pent_i - pent_j
			# If the difference is at least as big as a found difference, then stop testing lower pentagonal numbers
			if min_d != -1 and diff >= min_d:
				break
			elif is_pentagonal_number(pent_i + pent_j) and is_pentagonal_number(diff) and (min_d == -1 or diff < min_d):
				min_d = diff  # Found a smaller difference
	return str(min_d)


def pentagonal_number(x):
	assert x > 0
	return (x * (x * 3 - 1)) >> 1


def is_pentagonal_number(y):
	if y <= 0:
		return False
	# If y = pentagonal_number(x) = x(3x-1) / 2,
	# then by the quadratic formula, the positive solution is x = (sqrt(24y + 1) + 1) / 6.
	# There exists a solution for x if and only if both of these conditions hold:
	# (24y + 1) is a perfect square, and sqrt(24y + 1) + 1 mod 6 = 0.
	# The second condition is equivalent to sqrt(24y + 1) = 5 mod 6.
	temp = y * 24 + 1
	sqrt = eulerlib.sqrt(temp)
	return sqrt * sqrt == temp and sqrt % 6 == 5


if __name__ == "__main__":
	print(compute())
