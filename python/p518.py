# 
# Solution to Project Euler problem 518
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https:#www.nayuki.io/page/project-euler-solutions
# https:#github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, math, itertools


# Suppose (a+1, b+1, c+1) are three positive integers that form a geometric sequence.
# Then by definition, (c+1)/(b+1) = (b+1)/(a+1) = r for some ratio r.
# r is a rational number because we are given that a, b, c are integers.
# 
# Because of this, we can express r = z / y in lowest terms (i.e. y and z are coprime).
# Note that since the solution requires a < b < c, we require r > 1, hence z > y > 0.
# 
# Let's define x = (a+1) / y^2. Then we rearrange to get a + 1 = x * y * y.
# We argue that x is an integer. Look at (c+1)/(a+1) = z^2/y^2. y^2 and z^2 are coprime,
# thus the simplified denominator y^2 must divide the original denominator of a+1.
# 
# With {x, y, z} defined, we have b + 1 = x * y * z and c + 1 = x * z * z.
# Therefore every possible solution (a+1, b+1, c+1) can be re-expressed as
# a triple of integers (x, y, z) such that x >= 1, y >= 1, z > y, and gcd(y,z) = 1.
# In fact, this mapping of (a+1, b+1, c+1) to (x, y, z) is unique - this is because
# the ratio (b+1)/(a+1) uniquely determines y and z; subsequently a and y together give x.
# 
# The rest of the algorithm is a matter of searching x, y, z in some ascending order, and stopping
# each loop when no more candidates are possible because they all necessarily exceed the limit.
def compute():
	LIMIT = 10**8
	
	ans = 0
	isprime = eulerlib.list_primality(LIMIT - 1)
	
	# Search all possible x's. We know that c = x * z * z - 1. With the requirement c < LIMIT, we have x * z * z <= LIMIT.
	# Because z > y > 0, we know z >= 2. So at the very least we require x * 4 <= LIMIT. This implies x <= floor(LIMIT/4).
	for x in range(1, LIMIT // 4 + 1):
		
		# Search all possible y's. Notice that when y increases, 'a' strictly increases.
		# So when some y generates an 'a' such that a >= LIMIT, no candidates are possible with higher values of y.
		for y in itertools.count(1):
			a = x * y * y - 1
			if a >= LIMIT:
				break
			if not isprime[a]:
				continue
			
			# Search all valid z's. We require z > y and gcd(y, z) = 1. Notice that when z increases, c strictly increases.
			# So when some z generates a c such that c >= LIMIT, no candidates are possible with higher values of z.
			for z in itertools.count(y + 1):
				if math.gcd(y, z) != 1:
					continue
				c = x * z * z - 1
				if c >= LIMIT:
					break
				
				# Check whether (a, b, c) is a solution
				if isprime[c]:
					b = x * y * z - 1
					if isprime[b]:
						ans += a + b + c
	
	return str(ans)


if __name__ == "__main__":
	print(compute())
