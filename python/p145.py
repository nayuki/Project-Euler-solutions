# 
# Solution to Project Euler problem 145
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# First we define an n-digit number as a number beginning with the digit 1 to 9 (i.e. not 0) and having (n-1)
# of any digit thereafter. For example, 10652 is a 5-digit number, but 08913 = 8913 is a 4-digit number.
# 
# Lucky for us, the problem bound of 10^9 means that we examine all 1-to-9-digit numbers and not need
# to exclude some subrange. Note that we need to exclude all numbers ending in 0, so that the
# reversed number does not have leading zeros.
# 
# Consider different cases for the number of digits n (from 1 to 9, but the arguments apply generally):
# - n = 1:
#   Clearly there are no solutions because the last digit is always even.
# 
# - n = 0 mod 2:
#   We begin by proving that when a number is "reversible", the process of adding
#   the number to the reverse of itself will involve no carries in the arithmetic.
#   Normally a rigorous proof would require the use of mathematical induction,
#   but instead we will do illustratively with the specific case of n = 6.
#   A 6-digit number looks like abcdef, where each of 0 <= {a,b,c,d,e,f} <= 9, except a != 0.
#   When we add the number to its reverse, we get a structure like this:
#       abcdef
#     + fedcba
#     --------
#      tuvwxyz
#   For the number to be considered reversible, every digit in
#   the sum tuvwxyz needs to be odd, i.e. in the set {1,3,5,7,9}.
#   
#   First look at the middle two columns. If the 4th column d + c = x generates a carry-out, then
#   the 3rd column c + d + 1 = w receives a carry-in. But this would in turn require the 4th column to
#   have a carry-in, because otherwise x and w would have opposite evenness/oddness. Conversely if the
#   4th column has no carry-out, then the 3rd column has no carry-in and also must have no carry-out.
#     We can extend this argument outward from the middle. If the 5th column has a carry-out, then by
#   the above argument it would cause the 4th and 3rd columns to have a carry-out, which implies
#   the 2nd column has a carry-in, which would require the 5th column to have a carry-in.
#     But once we get to the rightmost column, we know by definition that it has no carry-in.
#   Hence the leftmost column has no carry-in, which implies the 2nd column has no carry-out.
#     Therefore the whole sum has no carries at all. This lemma is good news because
#   it means we can treat each column separately without worrying about their interactions.
#   
#   Let's look at the first column with a + f = u (with u < 10 because there is no carry-out). When we
#   choose a digit value for 'a', there is a set of values we can choose for 'f' so that their sum is odd.
#   'a' cannot be 0. When 'a' is 1, 'f' can be {2, 4, 6, 8} (not 0) and not produce a carry. When 'a' is 2,
#   'f' can be {1, 3, 5, 7} (not 9) and not produce a carry. We can list all the possibilities for (a, f):
#     (1,2), (1,4), (1,6), (1,8),
#     (2,1), (2,3), (2,5), (2,7),
#     (3,2), (3,4), (3,6),
#     (4,1), (4,3), (4,5),
#     (5,2), (5,4),
#     (6,1), (6,3),
#     (7,2),
#     (8,1).
#   We see above that there are 20 choices for (a,f). For the middle digits
#   (not first or last digit), we can use 0 so there are 30 choices:
#     (0,1), (0,3), (0,5), (0,7), (0,9),
#     (1,0), (1,2), (1,4), (1,6), (1,8),
#     (2,1), (2,3), (2,5), (2,7),
#     (3,0), (3,2), (3,4), (3,6),
#     (4,1), (4,3), (4,5),
#     (5,0), (5,2), (5,4),
#     (6,1), (6,3),
#     (7,0), (7,2),
#     (8,1),
#     (9,0).
#   Therefore by combinatorics, there are 20 * 30^(n/2 - 1) reversible n-digit numbers when n is even.
#   
# - n = 1 mod 2:
#   Let's illustrate what happens with a 7-digit number abcdefg:
#     0101010
#     abcdefg
#   + gfedcba
#   ---------
#    stuvwxyz
#   The middle column d + d = w will be even unless it has a carry-in from its right neighbor, so this
#   carry is required. Hence the 4th column has a carry-in, which means the 5th column has a carry-out.
#   By symmetry since 5th column carries out, then the 3rd column c + e = v must carry out as well.
#   (This is true even in the worst case if 5th column has a carry-in but the 3rd column has no carry-in,
#   because the sum must be odd and at least 11 so even if it drops to 10 there will still be a carry-out.)
#   Because the 2nd column receives a carry-in, the 6th column must receive a carry-in to maintain parity.
#     What this shows is that when the number of digits is odd, the middle column must have a carry-in,
#   and columns that are an even distance away from it must have a carry-in. This means it is impossible
#   to have a reversible number of length n = 1 mod 4, because that would force the rightmost column
#   to have a carry-in, which is impossible by definition. Thus we require n = 3 mod 4.
#     Let's analyze a bit further. The rightmost (7th) column has no carry-in by definition.
#   So the leftmost (1st) column must have no carry-in to ensure that both t and z are odd.
#   Then the 2nd column must have no carry-out, which implies the 6th column has no carry-out.
#   This is why we get the alternating pattern of carries in the adding process.
#   
#   The rest of the work is to enumerate the possibilities for each type of digit(s) in the number:
#   - Pairs of digits which take no carry and must generate a carry (20 choices):
#     (9,8), (9,6), (9,4), (9,2),
#     (8,9), (8,7), (8,5), (8,3),
#     (7,8), (7,6), (7,4),
#     (6,9), (6,7), (6,5),
#     (5,8), (5,6),
#     (4,9), (4,7),
#     (3,8),
#     (2,9).
#     Note that the first and last digits fall into this category, and there are no 0s at all.
#   - Non-middle pairs of digits which take a carry and generate no carry (25 choices):
#     (0,0), (0,2), (0,4), (0,6), (0,8),
#     (1,1), (1,3), (1,5), (1,7),
#     (2,0), (2,2), (2,4), (2,6),
#     (3,1), (3,3), (3,5),
#     (4,0), (4,2), (4,4),
#     (5,1), (5,3),
#     (6,0), (6,2),
#     (7,1),
#     (8,0).
#   - Middle single digit, which takes a carry and generates no carry (5 choices): 0, 1, 2, 3, 4.
#   All in all, there are 5 * 20^((n + 1)/4) * 25^((n - 3)/4) = 100 * 500^((n - 3)/4)
#   reversible n-digit numbers when n = 3 mod 4.
def compute():
	def count_reversibles(numdigits):
		if numdigits % 2 == 0:
			return 20 * 30**(numdigits // 2 - 1)
		elif numdigits % 4 == 3:
			return 100 * 500**((numdigits - 3) // 4)
		elif numdigits % 4 == 1:
			return 0
		else:
			raise AssertionError()
	
	ans = sum(count_reversibles(d) for d in range(2, 10))
	return str(ans)


if __name__ == "__main__":
	print(compute())
