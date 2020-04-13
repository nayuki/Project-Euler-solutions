# 
# Solution to Project Euler problem 135
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Let x = m, y = m - k, z = m - 2k (this is an arithmetic sequence).
# By tedious but straightforward algebra, x^2 - y^2 - z^2 = (m - k)(5k - m).
# 
# For the sequence to have positive descending terms, we require m > 0, k > 0, and 2k < m.
# We know y = m - k > 0, so this term in the product (m - k)(5k - m) is positive.
# Since we want x^2 - y^2 - z^2 > 0, this means we also require the term (5k - m) > 0, so 5k > m.
# Putting these facts together, we have m/5 < k < m/2. Note: k > m/5 is equivalent to k >= floor(m/5) + 1.
# 
# As for the search range of m, since (m - k) and (5k - m) are both positive integers,
# we know that (m - k)(5k - m) >= m - k > m/2. So m/2 < x^2 - y^2 - z^2 < 10^6.
# This means we search with 0 < m < 2 * 10^6.
def compute():
	LIMIT = 10**6
	solutions = [0] * LIMIT
	for m in range(1, LIMIT * 2):
		for k in range(m // 5 + 1, (m + 1) // 2):
			temp = (m - k) * (k * 5 - m)
			if temp >= LIMIT:
				break
			solutions[temp] += 1
	
	ans = solutions.count(10)
	return str(ans)


if __name__ == "__main__":
	print(compute())
