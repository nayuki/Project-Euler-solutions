# 
# Solution to Project Euler problem 549
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


# For any n >= 2, how do we compute s(n) = m, where m is the smallest integer such that m! is divisible by n?
# Two important simple observations:
# - n! is clearly divisible by n. So 2 <= s(n) <= n.
# - If n divides m!, then n also divides (m+1)!, (m+2)!, etc. (i.e. all factorials after m).
#   Thus a possible strategy is to find lower bounds for m, and refine our way up to the real answer.
# We begin by factorizing n as a product of powers of unique prime numbers: n = p_0^k_0 * p_1^k^1 * ... .
# 
# Now for each index i in this product, look at the prime power p_i^k_i.
# There is some smallest j such that j! is divisible by p_i^k_i.
# We argue this j must be a multiple of p_i. This is because if j is not a multiple of p_i,
# then the factorization of (j-1)! contains exactly as many copies of the factor p_i as j!.
# Or to look at it another way, as j increases, the value j! gains new copies of the factor p_i
# every time j's factorization contains p_i. (For example if we are interested in p = 2, then
# 2 = 2 (has 1 copy of 2), 4 = 2 * 2 (has 2 copies), 6 = 2 * 3 (has 1 copy), 8 = 2 * 2 * 2 (has 3 copies).)
# 
# For each index i in the prime factorization, we compute the smallest j such that p_i^k_i divides j!.
# We argue that the maximum among all these j's is the answer m = s(n).
# By construction, every p_i^k_i divides this m! and m! > 0. Because all these p_i^k_i's are pairwise coprime,
# by the Chinese remainder theorem, {the product of all these p_i^k_i's} also divides m!.
# 
# One consequence is that if n contains a big prime factor p, then m must be at least p.
# This is because otherwise if m < p, then m! will not contain a factor of p.
# For example if n = 245262 = 2 * 3 * 41 * 997, then s(n) >= 997. In fact s(n) = 997.
# 
# With this derivation in hand, we can work backwards, starting from primes
# and figuring out the smallest divisible factorial for each number.
# 
# Let's look at p = 2, the smallest prime number. Every number that is a multiple of 2 (including 2 itself)
# will be affected by how many copies of 2 are in the factorization of some factorial.
# - First we look at j = p = 2. The factorization of j! = 2! has 1 copy of 2. So we know that every number
#   whose factorization has at least 1 copy of 2 will have a smallest-divisible-factorial of at least j = 2.
#   We update all numbers that are multiples of 2.
# - Next we look at j = 2p = 4. The factorization of j! = 4! has 3 copies of 2. So we know that every number
#   whose factorization has at least 2 copies of 2 will have a smallest-divisible-factorial of at least j = 4.
#   We update all numbers that are multiples of 2^2 = 4.
# - Next we look at j = 3p = 6. The factorization of j! = 6! has 4 copies of 2. So we know that every number
#   whose factorization has at least 4 copies of 2 will have a smallest-divisible-factorial of at least j = 6.
#   We update all numbers that are multiples of 2^4 = 16.
# We keep increasing j until p^j exceeds the limit, in which case there are no more numbers to update.
# Note that in this process, we update all numbers that are multiples of 2, but no numbers that are not multiples of 2.
# Because of this, the next lowest number that has never been updated (i.e. 3) must be a prime number,
# and this process is effectively a modified version of the sieve of Eratosthenes. In other words, we can
# use a single array to both sieve prime numbers and compute the smallest-divisible-factorial simultaneously.
def compute():
	LIMIT = 10**8
	
	# Modification of the sieve of Eratosthenes
	smallestdivisiblefactorials = [0] * (LIMIT + 1)
	for i in range(2, len(smallestdivisiblefactorials)):
		if smallestdivisiblefactorials[i] == 0:
			# Now we know that i is prime
			
			power = 1
			for j in itertools.count(i, i):
				# We know j contains at least one factor of i
				power *= i
				if power > LIMIT:
					break
				
				# Update answer for all multiples of 'power'
				for k in range(power, len(smallestdivisiblefactorials), power):
					smallestdivisiblefactorials[k] = max(j, smallestdivisiblefactorials[k])
				
				# Update power to include the remaining factors of i in j
				temp = j // i
				while temp % i == 0:
					power *= i
					temp //= i
	
	ans = sum(smallestdivisiblefactorials)
	return str(ans)


if __name__ == "__main__":
	print(compute())
