# 
# Solution to Project Euler problem 265
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# In this problem we look at 2^n-digit binary strings and the n-digit substrings of these.
# We are given that n = 5, so we are looking at windows of 5 bits in 32-bit strings.
# 
# There are of course 32 possible cyclic windows in a 32-bit string.
# We want each of these windows to be a unique 5-bit string. There are exactly 2^5 = 32
# possible 5-bit strings, hence the 32 windows must cover the 5-bit space exactly once.
# 
# The result requires the substring of all zeros to be in the most significant bits.
# We argue that the top n bits must be all zeros, because this is one of the cyclic windows
# and the value 00...00 must occur once. Furthermore the next and previous bit must be 1 -
# because if they're not, then at least one of the adjacent windows are also zero, which
# violates the uniqueness requirement.
# 
# With n = 5, this means every candidate string must start with 000001 and end with 1.
# In other words, they are of the form 000001xxxxxxxxxxxxxxxxxxxxxxxxx1.
# The middle 25 bits still need to be determined, and we simply search by brute force.
def compute():
	N = 5  # Must be at least 1
	TWO_POW_N = 2**N
	MASK = TWO_POW_N - 1  # Equal to n 1's in binary, i.e. 0b11111
	
	def check_arrangement(digits):
		seen = set()
		digits |= digits << TWO_POW_N  # Make second copy
		for i in range(TWO_POW_N):
			seen.add((digits >> i) & MASK)
		return len(seen) == TWO_POW_N
	
	start = 2**(TWO_POW_N - N - 1) + 1
	end = 2**(TWO_POW_N - N)
	ans = sum(i for i in range(start, end, 2) if check_arrangement(i))
	return str(ans)


if __name__ == "__main__":
	print(compute())
