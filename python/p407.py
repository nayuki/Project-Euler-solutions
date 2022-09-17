# 
# Solution to Project Euler problem 407
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# If a^2 = a mod n, then this is also true for any m that divides n.
# Let's focus on the moduli that are prime powers, p^k.
# 
# Claim: The only solutions of a^2 = a mod p^k are a = 0, 1 mod p^k.
# Proof:
#   First note that a = 0 mod p^k is always a solution. Now consider the case of 0 < a < p^k.
#   Let a = b * p^j, where 0 < b < p^j and b is coprime with p (thus j is as large as possible).
#   Then (b p^j)^2 = b p^j mod p^k, expanding to b^2 p^2j = b p^j mod p^k.
#   Divide all of the equation (including the modulus) by p^j, giving b^2 p^j = b mod p^(k-j).
#   b is coprime with p (and therefore p^(k-j)), so b^-1 exists.
#   Multiply both sides by b^-2 to get b^-1 = p^j mod p^(k-j).
#   b is coprime with p, so b is not a power of p unless j = 0, i.e. p^j = 1 = b.
#   So when a != 0, a = 1 is the only solution.
# 
# If we factor n as a product of prime powers, i.e. n = p0^k0 * p1^k1 * ... where
# all the p's are distinct (and thus all the k's are as large as possible), then we have
# a system of congruences {a = 0,1 mod p0^k0; a = 0,1 mod p1^k1; ...}.
# Using the Chinese remainder theorem, we can solve these congruences to obtain the
# 2^N distinct solutions (where N is the number of distinct prime factors of n).
# The largest solution among these is what we want for the M() function.
def compute():
	LIMIT = 10**7
	
	smallestprimefactor = eulerlib.list_smallest_prime_factors(LIMIT)
	
	ans = 0
	for i in range(1, LIMIT + 1):
		# Compute factorization as coprime prime powers. e.g. 360 = {2^3, 3^2, 5^1}
		factorization = []
		j = i
		while j != 1:
			p = smallestprimefactor[j]
			q = 1
			while True:
				j //= p
				q *= p
				if j % p != 0:
					break
			factorization.append(q)
		
		solns = [0]
		modulus = 1
		for q in factorization:
			# Use Chinese remainder theorem; cache parts of it
			recip = pow(q, -1, modulus)
			newmod = q * modulus
			solns = [((0 + (x    ) * recip * q) % newmod) for x in solns] + \
			        [((1 + (x - 1) * recip * q) % newmod) for x in solns]
			modulus = newmod
		
		ans += max(solns)
	return str(ans)


if __name__ == "__main__":
	print(compute())
