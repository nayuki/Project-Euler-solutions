# 
# Solution to Project Euler problem 106
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# Lemma to confirm denominator:
#   For each natural number n >= 2, any set of size n has exactly
#   (3^n + 1) / 2 - 2^n unordered pairs of non-empty disjoint subsets.
# Proof:
#   0. Let A be an arbitrary set of size n. We want to count its subset pairs.
#   1. Suppose we can label each element of A with a letter from the set {B, C, N}.
#      A label is defined as an n-tuple of letters from the set {B, C, N}.
#      For example, the set {a,b,c,d} can be labeled as (B,B,C,N) or (N,C,N,N).
#   2. Let L be the set of all possible labels on A. We know that |L| = 3^n.
#   3. If a label doesn't contain any B's, then it is equivalent to saying that the
#      label is composed of any number of C's and N's, so there are 2^n of them.
#   4. If a label doesn't contain any C's, then it is equivalent to saying that the
#      label is composed of any number of B's and N's, so there are 2^n of them.
#   5. If a label doesn't contain any B's or C's, then it is the singleton of all N's.
#   6. How many labels contain at least one B and at least one C?
#      Using the inclusion-exclusion principle, we have:
#      |Have B and Have C| = |All labels| - |Lacks B or Lacks C|
#          = |All labels| - (|Lacks B| + |Lacks C| - |Lacks B and Lacks C|)
#          = |All labels| - |Lacks B| - |Lacks C| + |Lacks B and Lacks C|
#          = 3^n - 2^n - 2^n + 1.
#   7. For an arbitrary label that has at least one B and at least one C,
#      what actually matters is which elements of A are put into one subset
#      and which elements of A are put into the other disjoint subset, but
#      the names of these subsets as B and C are interchangeable. Hence we
#      divide by 2 to remove this degree of freedom, giving us a final count
#      of (3^n - 2^n - 2^n + 1) / 2 = (3^n + 1) / 2 - 2^n.
# Corollary:
#   This confirms some values mentioned in the problem statement:
#   - Set size n = 7 has 966 subset pairs.
#   - Set size n = 12 has 261625 subset pairs.
# 
# 
# Main theorem:
#   Let A be an arbitrary set such that all of the following hold:
#   - Its size is n.
#   - It consists only of positive integers.
#   - It satisfies the property (ii) given in the problem statement.
#   - a0 < a1 < ... < a_{n-1} are all the elements of A listed in ascending order.
#   To verify property (i), we would need to test some number of unordered pairs
#   of non-empty disjoint subsets of A to see that the subsets have unequal sums
#   Then we claim that exactly this many pairs need to be tested for equality:
#   The summation of (n choose 2k) * [(2k choose k) / 2 - (2k choose k) / (k + 1)]
#   for k from 2 to floor(n / 2) (inclusive).
# 
# Proof:
#   We begin by arguing about what subset pairs don't need to be tested,
#   then progress to counting which subset pairs do and don't need to be tested.
#   
#   Let B and C be an arbitrary pair of non-empty disjoint subsets of A.
#   Furthermore, restrict the pair  (and prevent duplicate counting) so that
#   the smallest element of B is smaller than the smallest element of C.
#   Assume that for each element of B and C, we know what index of A
#   it comes from, but we don't look at the actual element's value.
#   
#   If sets B and C have different sizes, then property (ii) implies that
#   either S(B) < S(C) or S(B) > S(C), which in both cases imply S(B) != S(C).
#   Hence we only care about the cases where |B| = |C|.
#   
#   If |B| = |C| = 1, then we know by disjointness that S(B) != S(C).
#   (Namely because each set has a different singleton element.)
#   
#   For the interesting case, we have |B| = |C| >= 2. If we can match each
#   element of B to a unique larger element in C, then we know for sure that
#   S(B) < S(C), which further implies that S(B) != S(C). When we find such
#   a matching, the ordering of the elements of A already implies inequality,
#   without the need to examine the actual element values.
#   
#   To illustrate with a concrete example, suppose B = {a0,a1} and C = {a2,a3}
#   are subsets of some set A. For each element of the set B or C, we are
#   assumed to know what index of A the element came from. Because we know
#   a0 < a2 and a1 < a3, we add these inequalities to get S(B) = a0 + a1
#   < a2 + a3 = S(C), implying S(B) != S(C). Similarly, with B = {a0,a2} and
#   C = {a1,a3}, we have a0 < a1 and a2 < a3, leading to a0 + a2 < a1 + a3.
#   But in the case of B = {a0,a3} and C = {a1,a2}, we cannot conclude
#   whether S(B) equals S(C) without examining the actual element values.
#   
#   Let's imagine scanning the elements of A in ascending order. When we
#   encounter an element a_i that is:
#   - In B, then we push it onto a stack, remembering that we need to
#     pair it with a later (and thus larger) element that is in C.
#   - In C, then we consult the stack. If the stack is empty, then this
#     element cannot be paired with an earlier (and thus smaller) element that
#     is in B, so no match exists. Otherwise we pop one element from the stack.
#   - Not in B or C, then we ignore it because it plays no role in the sums.
#   
#   Suppose we lay out the elements of A as a sequence, and label each element
#   according to the subsets B and C in a particular way. If element a_i is in B,
#   then label it as ( (left parenthesis). If element a_i is in C, then label it
#   as ) (right parenthesis). Otherwise label it as nothing/space.
#   
#   To illustrate, suppose A has size 5, B = {a0,a1}, and C = {a2,a3}.
#   Then this pair of subsets corresponds with the label "(( ))" on A.
#   
#   We can see that a pair of subsets doesn't need to be tested if
#   its label corresponds to a string of balanced parentheses. This is
#   a well-known combinatorics problem (which I won't try to prove):
#   The Catalan number C_k = (2k choose k) / (k + 1) represents the
#   number of ways that k pairs of parentheses can be arranged in a
#   sequence to produce a proper expression (i.e. no prefix contains
#   more right parentheses than left parentheses).
#   
#   There are (n choose 2k) ways to choose elements from A that will
#   then be split among the subsets B and C. Focus on one arbitrary choice.
#   
#   Subsequently, there are (2k choose k) / 2 ways to put k of those
#   elements into B and k of those elements into C, but without
#   regards to the ordering of B and C.
#   
#   However, C_k = (2k choose k) / (k + 1) of those choices of subset
#   pairs will necessarily have unequal sums due to the ordering of
#   elements, hence they don't need to be tested.
#   
#   This means that for a given k >= 2, we need to test (n choose 2k)
#   * ((2k choose k) / 2 - (2k choose k) / (k + 1)) subset pairs.
#   
#   Finally, we sum this term for k = 2, 3, 4, ... as long as 2k <= n,
#   so that we consider all the sizes of k where we can take a pair
#   of k-sized disjoint subsets of A.
#   
# Corollary:
#   Although the derivation/justification is long, the amount of
#   arithmetic is small enough to be doable by hand calculation.
def compute():
	SET_SIZE = 12
	
	def catalan(n):
		return eulerlib.binomial(n * 2, n) // (n + 1)
	
	ans = sum(eulerlib.binomial(SET_SIZE, i * 2) * (eulerlib.binomial(i * 2, i) // 2 - catalan(i))
		for i in range(2, SET_SIZE // 2 + 1))
	return str(ans)


if __name__ == "__main__":
	print(compute())
