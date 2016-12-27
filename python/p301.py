# 
# Solution to Project Euler problem 301
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# In a game of Nim where both players play with perfect strategy, if the current state is a collection (multiset) of piles
# with sizes {n1, n2, ..., n_m}, then the current player will lose if and only if n1 XOR n2 XOR ... XOR n_m = 0.
# In this problem, we specialize the condition to just n XOR 2n XOR 3n = 0.
# 
# Facts:
#   3n = 2n + n.
#   a ^ b = 0 iff a = b. (from digital logic)
#   a + b = (a ^ b) + ((a & b) << 1). (from digital logic)
# Hence:
#   n ^ 2n ^ 3n = 0                          (what we want)
#   iff n ^ 2n = 3n                          (from the second fact)
#   iff n ^ 2n = (n ^ 2n) + ((n & 2n) << 1)  (from the third fact)
#   iff (n & 2n) << 1 = 0                    (by cancelling on both sides)
#   iff n & 2n = 0                           (left-shifting doesn't change zeroness)
#   iff the binary representation of n does not have consecutive '1' bits.
# 
# How many binary strings of length i have no consecutive 1's?
#   First partition the set into strings that begin with a 0 and strings that begin with a 1.
#   For those that begin with a 0, the rest of the string can be any string of length i-1 that doesn't have consecutive 1's.
#   For those that begin with a 1, the rest of the string can be any string of length i-1 that begins with a 0 and doesn't have consecutive 1's.
# Let numStrings(i, j) be the number of bit strings of length i that begin with the bit j and have no consecutive 1's. Then:
#   numStrings(1, 0) = 1.  (base case)
#   numStrings(1, 1) = 1.  (base case)
#   numStrings(i, 0) = numStrings(i-1, 0) + numStrings(i-1, 1).  (for i >= 2)
#   numStrings(i, 1) = numStrings(i-1, 0).                       (for i >= 2)
# This corresponds to a shifted Fibonacci sequence, because:
#   numStrings(i, 0) = numStrings(i-1, 0) + numStrings(i-2, 0).  (substitute)
#   numStrings(1, 0) = 1.  (base case)
#   numStrings(2, 0) = 2.  (derived)
#   So numStrings(i, 0) = fibonacci(i + 1).
# What we want is numStrings(30, 0) + numStrings(30, 1) = numStrings(31, 0) = fibonacci(32).
# 
# Actually, that answer considers numbers in the range [0, 2^30), which is not exactly what we want.
# According to the problem statement, we need to exclude 0 and include 2^30. But both are losing positions, so the adjustments cancel out.
def compute():
	a = 0
	b = 1
	for i in range(32):
		a, b = b, a + b
	return str(a)


if __name__ == "__main__":
	print(compute())
