# 
# Solution to Project Euler problem 500
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, heapq


# Fact: When a number is factorized as a product of powers of unique primes, the number of factors
# the number has is equal to the product of {each exponent plus one}. That is, if n = p_1^k_1 *
# p_2^k_2 * ... * p_m^k_m, then n has exactly (k_1 + 1) * (k_2 + 1) * ... * (k_m + 1) factors.
# 
# Define any natural number n to be a "candidate" iff it has exactly 2^500500 factors (including 1 and n).
# 
# First examine n = 2^1 * 3^1 * 5^1 * 7^1 * ... * 7376497^1 * 7376507^1, which is the product of
# the first 500500 prime numbers. n has (1+1) * (1+1) * ... * (1+1) = 2 * 2 * ... * 2 = 2^500500
# factors, as desired. This candidate n is thus an upper bound on the answer we're looking for.
# 
# But if we drop the last prime and increase the exponent of the prime 2, then we
# can get a smaller candidate of n' = 2^3 * 3^1 * 5^1 * ... * 7376497^1. It has
# (3+1) * (1+1) * ... * (1+1) = 2^500500 factors, which makes it still valid.
# This means when we construct a candidate, we need to choose between including
# new prime factors versus increasing the exponent of an existing prime.
# 
# Let us illustrate the problem for small cases (checkable by brute force):
# - What is the smallest number with exactly 2^0 =  1 factor ? Clearly 1.
# - What is the smallest number with exactly 2^1 =  2 factors? It is 2.
# - What is the smallest number with exactly 2^2 =  4 factors? It is 2 * 3 = 6.
# - What is the smallest number with exactly 2^3 =  8 factors? It is 2^3 * 3 = 24.
# - What is the smallest number with exactly 2^4 = 16 factors? It is 2^3 * 3 * 5 = 120.
# 
# We observe that at each step (except the zeroth), the answer is equal to the
# previous answer times some number (unjustified claim). Some observations can be made:
# - We can multiply a number by an unused prime number to double its number of factors.
#   For example, 120 has 16 factors, and its highest prime factor is 5;
#   if we look at 120 * 7 = 840, then it has 16 * 2 = 32 factors.
# - It never pays to multiply by a larger prime than necessary (unjustified greedy claim).
#   For example, 120 * 11 = 1320 also has 16 * 2 = 32 factors, but 840 is a smaller choice.
#   Because we have an upper bound for the answer, we know we never need to use a prime larger than 7376507.
# - Instead of multiplying a new prime, we can increase the exponent of an existing prime.
#   For example, the prime power p^1 entails a factor of 1+1 = 2^1 in the number of factors of n;
#   the prime power p^3 entails a factor of 3+1 = 2^2 in the number of factors of n;
#   the prime power p^7 entails a factor of 7+1 = 2^3 in the number of factors of n;
#   the prime power p^15 entails a factor of 15+1 = 2^4 in the number of factors of n.
#   So when n does not contain the prime p, we multiply it by p^1 to double the number of factors of n.
#   Later on, we might multiply n by p^2, p^4, p^8, etc. to double the number of factors of n.
# 
# The solution algorithm works like this:
# 0. With respect to the answer product (which starts at 1), we always keep track of the
#    next power of each prime already used in the answer, as well as next unused prime.
#    For example, if the current answer is 2^7 * 3^3 * 5 * 7 * 11 * 13, then the set of next
#    powers is {2^8, 3^4, 5^2, 11^2, 13^2}, and the next prime is 17. We can
#    actually fuse the latter number into the former set, by denoting it as 17^1.
# 1. (Loop invariant) At the beginning of iteration i (0-based counting) of the main loop, the
#    current answer is the smallest number (modulo the modulus) that has exactly 2^i factors.
# 2. From the set of next powers/prime, remove the smallest item (a priority queue is needed), e.g. 17^1.
# 3. Multiply the current answer by the item, and reduce the answer by the modulus.
# 4. Put the square of the item back into the priority queue.
# 5. If the item is equal to the next unused prime, then increment
#    the next unused prime and put it into the priority queue.
def compute():
	TARGET = 500500
	MODULUS = 500500507
	isprime = eulerlib.list_primality(7376507)  # 500500th (1-based) prime number
	
	queue = []
	nextprime = 2
	heapq.heappush(queue, nextprime)
	
	ans = 1
	for _ in range(TARGET):
		item = heapq.heappop(queue)
		ans *= item
		ans %= MODULUS
		heapq.heappush(queue, item**2)
		
		if item == nextprime:
			nextprime += 1
			while not isprime[nextprime]:
				nextprime += 1
			heapq.heappush(queue, nextprime)
	
	return str(ans)


if __name__ == "__main__":
	print(compute())
