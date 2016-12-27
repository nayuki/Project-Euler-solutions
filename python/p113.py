# 
# Solution to Project Euler problem 113
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# Let n be the number of digits. To count the number of increasing or decreasing numbers using combinatorics,
# let's view each number as a sequence of digit readout slots and operations. For example, suppose n=5 and
# we examine the increasing number 23667. We can express it as the sequence "+ + # + # + + + # # + # + +",
# where # is a digit and + means increment. This way of thinking will be useful, as we will see.
# 
# For the set of increasing numbers, each number has n readout slots and 9 increments, positioned arbitrarily.
# Using this construction, the number is guaranteed to be increasing. Note that leading zeros can be produced.
# Conversely, for each increasing number, we can generate a (unique) sequence of slots and increments that represents it
# (putting all unused increments after the rightmost digit). Hence there are n+9 objects to arrange in sequence,
# so there are binomial(n + 9, 9) ways to arrange them. Finally we subtract 1 because 0 can be formed with this scheme,
# which must be excluded from the set of increasing numbers.
# 
# For the set of decreasing numbers, each number has n readout slots and 10 operations. Of the 10 operations,
# the leading one must be "increment to 9", and the rest must be decrements. Similar to the increasing case,
# each sequence of slots and decrements produces a decreasing number, and conversely each decreasing number
# corresponds to a unique sequence of slots and decrements. However, 0 can be formed in n+1 ways, by concentrating
# all 10 operations between some pair of slots, e.g. "+9 -9 # # # #", "# +9 -9 # # #", ..., "# # # # +9 -9".
# 
# There are 9n "flat" numbers, for example: 1, 2, ..., 9; 11, 22, ..., 99; 111, 222, ..., 999; ... (note that 0 is excluded).
# Since they are double-counted in the increasing and decreasing numbers, we subtract the size of this set.
# 
# In conclusion, the number of non-bouncy numbers is (binomial(n+9,9) - 1) + (binomial(n+10,10) - (n+1)) - 9n.
# 
# (Technically, in the problem statement and this solution, "increasing" actually means "nondecreasing" and "decreasing" means "nonincreasing".)
def compute():
	DIGITS = 100
	increasing = eulerlib.binomial(DIGITS + 9, 9) - 1
	decreasing = eulerlib.binomial(DIGITS + 10, 10) - (DIGITS + 1)
	flat = DIGITS * 9
	ans = increasing + decreasing - flat
	return str(ans)


if __name__ == "__main__":
	print(compute())
