# 
# Solution to Project Euler problem 94
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, math, itertools


# Consider an arbitrary almost equilateral triangle with side lengths (c, c, c +/- 1).
# Split it down the middle to get a right triangle, and label the new sides.
#     /\               /|
#  c /  \ c         c / | b
#   /    \    -->    /  |
#  --------         -----
#   c +/- 1           a
# Note that a = (c +/- 1) / 2, and a^2 + b^2 = c^2 (Pythagorean theorem).
# 
# We know that c is an integer. The area of the original triangle is a*b,
# which is an integer by definition from the problem statement.
# - If a is an integer, then b is an integer (so that a*b is an integer),
#   thus (a,b,c) is a Pythagorean triple.
# - Otherwise a is an integer plus a half, then b must be even,
#   but a^2 + b^2 is not an integer, which contradicts c being an integer.
# 
# Conversely, consider an arbitrary Pythagorean triple (a,b,c).
# If 2a = c +/- 1, then we can form an almost equilateral triangle:
#     /|\
#  c / | \ c
#   /  |  \
#  ---------
#      2a
# For this to happen, the Pythagorean triple must be primitive. Because if not,
# then a = 0 mod k and c = 0 mod k for some k > 1, which means 2a = 0 mod k which
# cannot equal c +/- 1 = +/- 1 mod k. So we only need to generate primitive triples.
# 
# Pythagorean triples theorem:
#   Every primitive Pythagorean triple with a odd and b even can be expressed as
#   a = st, b = (s^2-t^2)/2, c = (s^2+t^2)/2, where s > t > 0 are coprime odd integers.
def compute():
	LIMIT = 10**9
	ans = 0
	# What search range do we need?
	# c = (s^2+t^2)/2. Perimeter = p = 3c +/- 1 = 3/2 (s^2+t^2) +/- 1 <= LIMIT.
	# We need to keep the smaller perimeter within limit for
	# the search to be meaningful, so 3/2 (s^2+t^2) - 1 <= LIMIT.
	# With t < s, we have that s^2+t^2 < 2s^2, so 3/2 (s^2+t^2) - 1 < 3s^2 - 1.
	# Therefore it is sufficient to ensure that 3s^2 - 1 <= LIMIT, i.e. s^2 <= (LIMIT+1)/3.
	for s in itertools.count(1, 2):
		if s * s > (LIMIT + 1) // 3:
			break
		for t in range(s - 2, 0, -2):
			if math.gcd(s, t) == 1:
				a = s * t
				b = (s * s - t * t) // 2
				c = (s * s + t * t) // 2
				if a * 2 == c - 1:
					p = c * 3 - 1
					if p <= LIMIT:
						ans += p
				if a * 2 == c + 1:
					p = c * 3 + 1
					if p <= LIMIT:
						ans += p
				# Swap the roles of a and b and try the same tests
				# Note that a != b, since otherwise c = a * sqrt(2) would be irrational
				if b * 2 == c - 1:
					p = c * 3 - 1
					if p <= LIMIT:
						ans += p
				if b * 2 == c + 1:
					p = c * 3 + 1
					if p <= LIMIT:
						ans += p
	return str(ans)


if __name__ == "__main__":
	print(compute())
