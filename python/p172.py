# 
# Solution to Project Euler problem 172
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import math


# Highly customizable!
LENGTH = 18    # >= 1
MAX_COUNT = 3  # >= ceiling(LENGTH / BASE), else the result is 0
BASE = 10      # >= 2


# Let's explain how to solve this problem mostly by examples rather than by rigorous arguments.
# 
# We want to generate all the sequences of 18 decimal digits where each digit value (from '0' to '9') is used 0 to 3 times.
# We can partition the set of all possible sequences by considering, for each sequence, the multiset of digit value repetition counts.
# For example, the number 111222333444555666 has '1' used 3 times, '2' used 3 times, ..., and '6' used 3 times.
# When we look at these repetition counts, we see that 3 + 3 + 3 + 3 + 3 + 3 = 18. We always need them to sum to 18 ('LENGTH').
# 
# There are sequences with other repetition counts too. For example: 121212333444567890, the rep counts are 1 + 3 + 3 + 3 + 3 + 1 + 1 + 1 + 1 + 1.
# In fact, there are exactly 17 ways (partitions) to express 18 as an unordered sum of 10 terms with each term from 0 to 3:
# - 3 + 3 + 3 + 3 + 3 + 3 + 0 + 0 + 0 + 0
# - 3 + 3 + 3 + 3 + 3 + 2 + 1 + 0 + 0 + 0
# - 3 + 3 + 3 + 3 + 3 + 1 + 1 + 1 + 0 + 0
# - 3 + 3 + 3 + 3 + 2 + 2 + 2 + 0 + 0 + 0
# - 3 + 3 + 3 + 3 + 2 + 2 + 1 + 1 + 0 + 0
# - 3 + 3 + 3 + 3 + 2 + 1 + 1 + 1 + 1 + 0
# - 3 + 3 + 3 + 3 + 1 + 1 + 1 + 1 + 1 + 1
# - 3 + 3 + 3 + 2 + 2 + 2 + 2 + 1 + 0 + 0
# - 3 + 3 + 3 + 2 + 2 + 2 + 1 + 1 + 1 + 0
# - 3 + 3 + 3 + 2 + 2 + 1 + 1 + 1 + 1 + 1
# - 3 + 3 + 2 + 2 + 2 + 2 + 2 + 2 + 0 + 0
# - 3 + 3 + 2 + 2 + 2 + 2 + 2 + 1 + 1 + 0
# - 3 + 3 + 2 + 2 + 2 + 2 + 1 + 1 + 1 + 1
# - 3 + 2 + 2 + 2 + 2 + 2 + 2 + 2 + 1 + 0
# - 3 + 2 + 2 + 2 + 2 + 2 + 2 + 1 + 1 + 1
# - 2 + 2 + 2 + 2 + 2 + 2 + 2 + 2 + 2 + 0
# - 2 + 2 + 2 + 2 + 2 + 2 + 2 + 2 + 1 + 1
# For example, the number 912349441125323088 is associated with the partition (3 + 3 + 3 + 3 + 2 + 2 + 1 + 1 + 0 + 0),
# because '1' is used 3 times, '2' is used 3 times, '3' is used 3 times, '8' is used 2 times, '9' is used 2 times,
# '0' is used 1 time, '5' is used 1 time, '6' is used 0 times, and '7' is used 0 times.
# 
# For each partition, we want to take the 10 decimal digit values and assign them to the terms of the partition. Here is one example assignment:
#   Frequency:   3 3 3 3 | 2 2 | 1 1 | 0 0
#   Digit value: 1 2 3 4 | 8 9 | 0 5 | 6 7
# But note that order does not matter if the frequency is the same - for example, this means the same as above:
#   Frequency:   3 3 3 3 | 2 2 | 1 1 | 0 0
#   Digit value: 4 1 3 2 | 8 9 | 5 0 | 7 6
# 
# For a given partition, how many ways are there to assign digit values to the frequencies?
# Considering the frequencies and the digit values each as a length-10 sequence (like above),
# we know that there are 10! raw ways to arrange the digit values. But order within a frequency does not matter.
# So we divide by the factorial of the repetition count of each frequency. In the example above, the answer is 10! / (4! 2! 2! 2!).
# (Note that this is a multinomial coefficient.)
# 
# Now that we have a partition and a digit value assignment to the frequencies, we can generate permutations.
# For the example above, one possible sequence (and the lexicographically lowest) is 011122233344458899 (length 18).
# If we permute this sequence, the partition and digit-frequency assignments will remain the same.
# So we want to count how many permutations this sequence has.
# 
# Given a partition and a digit-frequency assignment, how many sequences have this classification?
# Because there are 18 digits, there are 18! raw arrangements of digits for the sequence.
# But for each digit value, it is repeated k times, so we need to divide by k! to suppress identical-looking arrangements.
# In this example, there are 18! / (3! 3! 3! 3! 2! 2! 2! 2! 2! 2!) arrangements.
# 
# Now, all of the arguments above have no made use of the specific digit values, so there is a certain symmetry in the set of desired sequences.
# In particular, this means exactly 9/10th of all items have a leading zero, hence we multiply by 9/10 to get the final answer.
def compute():
	ans = partition_and_count(LENGTH, MAX_COUNT, [])
	# Multiply by (base - 1) / base to discount sequences with leading zeros
	ans = divide_exactly(ans * (BASE - 1), BASE)
	return str(ans)


# Expresses 'LENGTH' as a sum of 'BASE' non-increasing terms, where terms to be added are in the range [0, max].
# e.g. partitionAndCount(7, 2, [3, 3, 2, 2, 1]) asks us to express 18 as a sum of 5 more terms,
# where the new terms have a sum of 7 and each is no greater than 2 and all terms are non-increasing.
def partition_and_count(sum, max, terms):
	if len(terms) == BASE:
		return count_ways(terms) if (sum == 0) else 0
	else:
		result = 0
		for i in reversed(range(min(max, sum) + 1)):
			terms.append(i)
			result += partition_and_count(sum - i, i, terms)
			terms.pop()
		return result


def count_ways(freqs):
	# The number of times each frequency value occurs
	histogram = [0] * (MAX_COUNT + 1)
	for x in freqs:
		histogram[x] += 1
	
	# Multinomial coefficient: BASE! / (histogram[0]! * histogram[1]! * ...)
	ways = math.factorial(BASE)
	for x in histogram:
		ways //= math.factorial(x)
	
	# Multinomial coefficient: LENGTH! / (freqs[0]! * freqs[1]! * ...)
	ways *= math.factorial(LENGTH)
	for x in freqs:
		ways //= math.factorial(x)
	
	return ways


def divide_exactly(x, y):
	if x % y != 0:
		raise ValueError("Not divisible")
	return x // y


if __name__ == "__main__":
	print(compute())
