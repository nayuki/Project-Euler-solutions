# 
# Solution to Project Euler problem 103
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


# We start with three pedantic lemmas to constrain the nature of possible solutions.
# 
# No-zero lemma:
#   A special sum set (SSS) should not contain the value 0. The problem statement
#   doesn't say this explicitly, but it is implied in the numerical examples.
# Proof:
#   - For size n = 0, {} technically qualifies as an SSS, and is trivially optimum.
#     But the problem statement does not mention the n = 0 case at all.
#   - For size n = 1, {0} has sum 0, which is better than the SSS {1} given in the
#     problem statement, yet this violates no properties. Even though the subsets
#     {} and {0} have the same sum of 0, the properties only apply to non-empty subsets.
#   - For size n = 2, {0,1} has sum 1, which is better than the SSS {1,2} given in
#     the problem statement, yet this still violates no properties. There is exactly
#     one possible pair of non-empty disjoint subsets, namely {0} vs. {1}, and
#     this pair satisfies both properties.
#   - For sizes n >= 3, having 0 in the set would violate property (ii) for the pair of
# 	subsets {0,a} and {b}, where 0 and a and b are distinct elements of the set and a < b.
#   Hence for 0 <= n <= 2, allowing 0 would produce a more optimum solution than the
#   examples given in the problem statement, and for n >= 3 an SSS can never contain 0.
# 
# As for negative numbers, the intent of the problem statement readily suggests that elements
# are never negative. Furthermore, having negative numbers in a set would either affect the
# sum by only a small amount or violate property (ii), making the problem uninteresting.
# 
# Upper bound lemma:
#   For each natural number n >= 0, there exists a special sum set
#   whose size is n and whose sum is (n + 1) * 2^n - 1.
# Proof:
#   - For each index i (counting from 0), let element i equal 2^n + 2^i.
#   - The sum of all the elements is (2^n + 2^0) + (2^n + 2^1) + ... + (2^n + 2^(n-1))
#     = n * 2^n + (2^0 + 2^1 + ... + 2^(n-1)) = n * 2^n + 2^n - 1 = (n + 1) * 2^n - 1.
#   - For example with n = 4, the elements expressed in binary are {10001,
#     10010, 10100, 11000}. Their sum is 1001111 (binary) = 79 (decimal).
#   - Notice that when summing a subset of (distinct) elements, the bottom n bits
#     never produce a carry. This means the bottom n bits behave like a set union,
#     and the activity in the bottom n bits never affect the 2^n term or above.
#   - Property (i) is satisfied because for any subset B, the bottommost n bits of S(B)
#     encode which elements are present. Thus any two unequal subsets will have unequal sums.
#   - Property (ii) is satisfied because for any subset B, the value floor(S(B) / 2^n)
#     (i.e. dropping the bottommost n bits) encodes the size of B.
# Corollaries:
#   - For any given upper bound, there are a finite number of {{sets of positive integers}
#     whose sum doesn't exceed the upper bound}. Thus once we find an SSS with a certain sum,
#     we can argue by finite search that there must exist an optimum SSS whose sum is
#     less than or equal to the aforementioned sum.
#   - For size 7, we know there exists an SSS of sum exactly (7 + 1) * 2^7 - 1 = 1023.
#     Hence we don't need to search any larger sums.
# 
# Lower bound lemma:
#   For each n >= 3, each special sum set of size n must have sum at least 2^n.
# Proof:
#   - A set of size n has exactly 2^n - 1 non-empty subsets
#     (the kind relevant to the problem statement).
#   - Because all elements are positive integers, the lowest possible
#     subset sum is 1, and the highest sum is the sum of all elements.
#   - To satisfy property (i) and give each non-empty subset a unique sum, the
#     set of sums with the smallest maximum value is {1, 2, 3, ..., 2^n - 1}.
#   - To achieve the aforementioned set of subset sums using positive elements,
#     the one and only solution is the set {1, 2, 4, 8, ..., 2^(n-1)}.
#   - But for n >= 3, the pair of subsets {1,2} and {4} violates property (ii).
#   - Hence the set cannot have sum 2^n - 1. It must have a sum of at least 2^n.
# Corollary:
#   We can begin searching for an optimum SSS with the initial maximum sum set to 2^n.
def compute():
	TARGET_SIZE = 7
	
	# At the top level, we try larger and smaller values of s until we find the smallest value
	# of s such that there exists a special sum set with sum at most s. As long as no solution
	# exists when the sum is at most s - 1, it means that the optimum set's sum is exactly s.
	
	# First we try maxsum = 1, 2, 4, 8, ..., doubling the maximum sum until we find a solution.
	maxsum = 1
	while SpecialSumSet.make_set(TARGET_SIZE, maxsum) is None:
		maxsum *= 2
	# Now we know that there must be a special sum set whose sum is at most
	# maxsum, and also that no solution exists for maxsum / 2.
	
	# Perform a kind of binary search to decrease maxsum to the optimal value.
	# For example, if maxsum = 256, then we know that a solution exists for
	# maxsum = 256 but no solution exists for maxsum = 128. We don't know if
	# maxsum = 129, 130, ..., 255 will yield solutions. We first try maxsum
	# = 256 - 64, and depending on whether a solution exists, we eliminate
	# either the bottom half of the search range or the top half. Then we
	# try smaller steps, and stop after handling a step size of 1.
	i = maxsum // 4
	while i > 0:
		maxsum -= i
		if SpecialSumSet.make_set(TARGET_SIZE, maxsum) is None:
			maxsum += i
		i //= 2
	
	set = SpecialSumSet.make_set(TARGET_SIZE, maxsum)  # Must be not None
	return "".join(map(str, set.values))



# This helper class represents a finite sequence of distinct positive integers
# that satisfies properties (i) and (ii) given in the problem statement.
# Objects of the class are immutable. Objects also keep track of extra data to
# make it easier to check if adding a new element would violate the properties,
# without explicitly checking every non-empty disjoint subset pair by brute force.
class SpecialSumSet:
	
	# Returns the lexicographically lowest special sum set with the given size
	# and with a sum of at most maximumsum, or None if no such set exists.
	@staticmethod
	def make_set(targetsize, maximumsum):
		return SpecialSumSet._make_set(SpecialSumSet([], [True], [0], [0]), targetsize, maximumsum, 1)
	
	
	# Returns the lexicographically lowest special sum set by adding exactly sizeremain elements
	# to the given set, such that the sum of the additional elements is at most sumremain,
	# and the next element to be added is at least startval. Returns None if no such set exists.
	@staticmethod
	def _make_set(set, sizeremain, sumremain, startval):
		# In summary, this procedure takes a partial answer (prefix) and some parameters,
		# and tries to extend the answer by performing depth-first search through recursion.
		
		if sizeremain == 0:  # Base case - success
			return set
		
		# Optimization: If we still need to add at least 2 elements, then the next element
		# will be at least startval, the one after will be at least startval + 1,
		# thereafter is at least startval + 2, et cetera. The sum of the elements
		# to be added is strictly greater than startval * sizeremain, which we can
		# check against sumremain and bail out early if a solution is impossible.
		if sizeremain >= 2 and startval * sizeremain >= sumremain:
			return None
		
		endval = sumremain
		# Optimization: If the partial set has at least two elements a and b, then by the
		# property (ii), S({a, b}) = a + b must be greater than any single element of the set.
		# We use the foremost two elements, which have the smallest values - this makes
		# endval as small and restrictive as possible compared to other choices of elements.
		if len(set.values) >= 2:
			endval = min(set.values[0] + set.values[1] - 1, endval)
		
		# Consider each possible value for the next element
		for val in range(startval, endval + 1):
			# Try adding the value and see if any property is violated
			temp = set.add(val)
			if temp is None:
				continue
			
			# Recurse and see if a solution is found down the call tree
			temp = SpecialSumSet._make_set(temp, sizeremain - 1, sumremain - val, val + 1)
			if temp is not None:
				return temp
		return None  # No solution for the given current state
	
	
	# Internal constructor. The contents of the given list objects must not change.
	def __init__(self, vals, sumposb, minsum, maxsum):
		# Note: All fields are conceptually immutable
		
		# Positive numbers in strict ascending order. Length 0 or more.
		self.values = vals
		
		# For indexes i from 0 to sum(values) inclusive, sumpossible[i]
		# is true iff there exists a subset of 'values' whose sum is i.
		self.sumpossible = sumposb
		
		# For i from 0 to len(values) (inclusive), minimumsum[i] is the minimum sum
		# among all possible subsets of 'values' of size i, and likewise for maximumsum[i].
		self.minimumsum = minsum
		self.maximumsum = maxsum
	
	
	# Attempts to add the given value to this set, returning a new set
	# if successful. Otherwise returns None if any property is violated.
	def add(self, val):
		# Simple checks on the value
		if val <= 0:
			raise ValueError("Value must be positive")
		size = len(self.values)
		if size >= 1 and val <= self.values[-1]:
			raise ValueError("Must add values in ascending order")
		
		# Check if adding val to any subset of this set would create a duplicate sum
		posb = self.sumpossible
		if any((posb[i] and posb[i - val]) for i in range(val, len(posb))):
			return None
		
		# Compute minimum and maximum sums for each subset size, with help from old data.
		# The idea is that by introducing the new value val, each subset of the new set
		# either contains val or doesn't. All old subsets are still possible, so we copy
		# the old tables of minima and maxima. Each new subset contains val plus an old subset
		# (possibly empty). Hence we look at the existing minima and maxima, add val to the sum,
		# add 1 to the size, and merge the values into the table of minima and maxima.
		newsize = size + 1
		minsum = self.minimumsum
		maxsum = self.maximumsum
		newmin = [0] + [min(minsum[i], minsum[i - 1] + val) for i in range(1, newsize)] + [minsum[size] + val]
		newmax = [0] + [max(maxsum[i], maxsum[i - 1] + val) for i in range(1, newsize)] + [maxsum[size] + val]
		
		# Check iff property (ii) '|B| > |C| implies S(B) > S(C)' is violated
		if any((newmax[i] >= newmin[i + 1]) for i in range(newsize)):
			return None
		
		# Compute all possible new subset sums, with help from old data. This is the
		# classic table-based algorithm for solving the subset sum or knapsack problem.
		newposb = posb + [False] * val
		for i in reversed(range(val, len(newposb))):
			newposb[i] |= newposb[i - val]
		
		# Append given value to the new set
		return SpecialSumSet(self.values + [val], newposb, newmin, newmax)
	
	# An illustrative example for SpecialSumSet and add():
	# 
	# Suppose our current set is {3, 5, 6}. All its subsets and their sums are:
	# - S({}) = 0.
	# - S({3}) = 3.
	# - S({5}) = 5.
	# - S({6}) = 6.
	# - S({3, 5}) = 8.
	# - S({3, 6}) = 9.
	# - S({5, 6}) = 11.
	# - S({3, 5, 6}) = 14.
	# 
	# Therefore, the data arrays have the following values:
	# - sumpossible = [T, F, F, T, F, T, T, F, T, T,  F,  T,  F,  F,  T].
	#   (Sum legend:   0  1  2  3  4  5  6  7  8  9  10  11  12  13  14)
	# - minimumsums = [0, 3,  8, 14].
	# - maximumsums = [0, 6, 11, 14].
	# - (Size legend:  0  1   2   3)
	# 
	# Now suppose we want to add the value 7 to the set. Here is what happens:
	# - First we check that in sumpossible, no pair of 'true' elements are
	#   separated by a distance of 7. This ensures that if we take any particular
	#   subset and add 7 to it, its sum won't equal another existing subset sum.
	# - Let the new set S' = S union {7} = {3, 5, 6, 7}. What are
	#   the minimum and maximum subset sums for each subset size k?
	#   - If k = 0, the min and max are both clearly 0.
	#   - If k = |S| = 4, then min and max are the sum of all elements, thus 21.
	#   - Otherwise with k > 0, consider an arbitrary subset A of S' where |A| = k.
	#     - If A does not contain 7, then A is a subset of S, so A's
	#       minimum sum is minimumsums[k] and A's maximum sum is maximumsums[k].
	#     - Otherwise split A = {7} union B, where B does not contain 7.
	#       B is a subset of S, and |B| = k - 1. So A's minimum sum is
	#       7 + minimumsums[k - 1], and A's maximum sum is 7 + maximumsums[k - 1].
	#     Hence newminimumsums[k] = min(minimumsums[k], 7 + minimumsums[k - 1]),
	#     and newmaximumsums[k] = max(maximumsums[k], 7 + maximumsums[k - 1]).
	#   For each size k that is not out of bounds, if maximumsums[k] >= minimumsums[k + 1],
	#   then there exists some set of size k with some set of size k + 1 fails property (ii).
	#   Otherwise property (ii) is upheld in all subset pairs (including empty subsets).
	# - Finally, we compute the new sumpossible table (which is guaranteed to
	#   have no conflicts), and finish creating the new set with the added element.


if __name__ == "__main__":
	print(compute())
