# 
# Solution to Project Euler problem 128
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


# Let's do mathematical analysis to drastically reduce the amount of
# logic we need to implement and calculation the computer needs to do.
# We begin with a couple of definitions.
# 
# Ring number: Each cell belongs in a hexagonal ring,
# numbered starting from 0 at the center like this:
#               3
#           3       3
#       3       2       3
#   3       2       2       3
#       2       1       2
#   3       1       1       3
#       2       0       2
#   3       1       1       3
#       2       1       2
#   3       2       2       3
#       3       2       3
#           3       3
#               3
# 
# Corner/edge cell: Within a ring, each cell is
# either a corner cell or an edge cell, as shown:
#               C
#           E       E
#       E       C       E
#   C       E       E       C
#       C       C       C
#   E       C       C       E
#       E       C       E
#   E       C       C       E
#       C       C       C
#   C       E       E       C
#       E       C       E
#           E       E
#               C
# 
# Basic observations:
# - Except for the degenerate ring 0, each ring k has 6k cells.
#   The kth ring has exactly 6 corner cells and 6(k - 1) edge cells.
# - In the code we will skip the PD (prime difference) calculation for
#   rings 0 and 1 because the existence of ring 0 breaks many patterns.
# - Doing the PD calculation for rings 0 and 1 by hand (n = 1 to 7
#   inclusive), we find that PD(n) = 3 for and only for n = 1, 2.
# 
# Now let's analyze the characteristics of all cells in rings 2 or above.
# It's hard to justify these assertions rigorously, but they are true from
# looking at the spiral diagram.
# 
# - Corner cells along the upward vertical direction and the edge cells
#   immediately to the right of this vertical column are the most interesting,
#   so we will save these cases for last.
# 
# - Claim: Except for cells immediately right of the upward corner column,
#   no edge cell satisfies PD(n) = 3. Proof: Take an arbitrary edge cell n
#   not immediately to the right of the upward corner column...
#   - The two neighbors in the same ring have a difference of 1 compared to n,
#     which is not a prime number.
#   - The two neighbors in the previous (inward) ring are consecutive numbers,
#     so exactly one of them has an even absolute difference with n. Because
#     n is in ring 2 or above, the difference with any neighboring number in the
#     previous ring is at least 6. Thus an even number greater than 2 is not prime.
#   - Similarly, the two neighbors in the next (outward) ring are consecutive numbers.
#     One of them has an even difference with n, and this number is also at least 6,
#     so one neighbor is definitely not prime.
#   - Therefore with at least 4 neighbors that do not have a prime difference, PD(n) <= 2.
#   Example of an edge cell n = 11 in ring 2, which is straight left of the origin:
#         10
#     24      03
#         11
#     25      04
#         12
# 
# - Claim: No corner cell in the other 5 directions satisfies PD(n) = 3.
#   Proof: Take an arbitrary corner cell n in the non-upward direction...
#   - Two of its neighbors (in the same ring) have a difference of 1,
#     which is not prime.
#   - One neighbor is in the previous ring (inward) while three neighbors
#     are in the next ring (outward).
#   - Let the inner ring neighbor be k and the outer ring's middle neighbor
#     be m. The three outer ring neighbors are {m - 1, m, m + 1}.
#   - Then n - k + 6 = m - n. Also, {m - 1, m + 1} have the same parity,
#     and {k, m} have the same other parity.
#   - Either both {|k - n|, |m - n|} are even or both {|m - 1 - n|, |m + 1 - n|} are even.
#     In any case, all these differences are at least 6, so the even numbers are not prime.
#   - Therefore with at least 4 neighbors that do not have a prime difference, PD(n) <= 2.
#   Example of a corner cell n = 14 in ring 2, which is straight below the origin:
#         05
#     13      15
#         14
#     28      30
#         29
# 
# - Now let's consider an arbitrary upward corner cell n in ring k, with k >= 2.
#   We shall give variables to all its neighbors like this:
#         d
#     e       f
#         n
#     b       c
#         a
#   - a is in the previous ring, {b, c} are in the same ring as n,
#     and {d, e, f} are in the next ring.
#   - Equations derived from the structure of the hexagonal spiral:
#     n = 3k(k - 1) + 2.
#     a = n - 6(k - 1).
#     b = n + 1.
#     c = n + 6k - 1 = d - 1.
#     d = n + 6k.
#     e = n + 6k + 1 = d + 1.
#     f = n + 6k + 6(k + 1) - 1 = n + 12k + 5.
#   - Hence we get these absolute differences with n:
#     |a - n| = 6(k - 1). (Not prime because it's a multiple of 6)
#     |b - n| = 1. (Not prime)
#     |c - n| = 6k - 1. (Possibly prime)
#     |d - n| = 6k. (Not prime because it's a multiple of 6)
#     |e - n| = 6k + 1. (Possibly prime)
#     |f - n| = 12k + 5. (Possibly prime)
#   - Therefore for each k >= 2, we need to count how many numbers
#     in the set {6k - 1, 6k + 1, 12k + 5} are prime.
#   Example of a corner cell n = 8 in ring 2, which is straight above the origin:
#         20
#     21      37
#         08
#     09      19
#         02
# 
# - Finally let's consider an arbitrary edge cell immediately to the right of the
#   upward vertical column. Suppose the cell's value is n and it is in ring k,
#   with k >= 2. Give variables to all its neighbors like this:
#         f
#     c       e
#         n
#     a       d
#         b
#   - {a, b} are in the previous ring, {c, d} are in the current ring, and {e, f} are in
#     the next ring. The ascending ordering of all these numbers is (a, b, c, d, n, e, f).
#   - Equations derived from the structure of the hexagonal spiral:
#     n = 3k(k + 1) + 1.
#     a = n - 6k - 6(k - 1) + 1 = n - 12k + 7.
#     b = n - 6k.
#     c = n - 6k + 1.
#     d = n - 1.
#     e = n + 6(k + 1) - 1 = n + 6k + 5.
#     f = n + 6(k + 1).
#   - Hence we get these absolute differences with n:
#     |a - n| = 12k - 7. (Possibly prime)
#     |b - n| = 6k. (Not prime because it's a multiple of 6)
#     |c - n| = 6k - 1. (Possibly prime)
#     |d - n| = 1. (Not prime)
#     |e - n| = 6k + 5. (Possibly prime)
#     |f - n| = 6(k + 1). (Not prime because it's a multiple of 6)
#   - Therefore for each k >= 2, we need to count how many numbers
#     in the set {6k - 1, 6k + 5, 12k - 7} are prime.
#   Example of an edge cell n = 19 in ring 2:
#         37
#     08      36
#         19
#     02      18
#         07
def compute():
	TARGET = 2000  # Must be at least 3
	count = 2  # Because n = 1 and 2 satisfy PD(n) = 3
	for ring in itertools.count(2):
		if all(map(eulerlib.is_prime, (ring * 6 - 1, ring * 6 + 1, ring * 12 + 5))):
			count += 1
			if count == TARGET:
				return str(ring * (ring - 1) * 3 + 2)
		if all(map(eulerlib.is_prime, (ring * 6 - 1, ring * 6 + 5, ring * 12 - 7))):
			count += 1
			if count == TARGET:
				return str(ring * (ring + 1) * 3 + 1)


if __name__ == "__main__":
	print(compute())
