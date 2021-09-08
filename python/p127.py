# 
# Solution to Project Euler problem 127
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import math


# A straightforward solution would look like this:
#   for c in range(2, LIMIT):
#      for a in range(1, c):
#        b = c - a
#        if is_abc_hit(a, b, c):
#          ans += c
# 
# Here are some observations that lead to optimizations:
# - For each integer n >= 2, we have 2 <= rad(n) <= n.
# - By Euclid's GCD algorithm, gcd(c,b) = gcd(a+b,b) = gcd(a,b) = gcd(a,a+b) = gcd(a,c).
#   Hence gcd(a,b) = 1 if and only if gcd(a,c) = 1 and gcd(b,c) = 1.
#   We only need to compute and check one of these three GCDs.
# - Since {a, b, c} are mutually coprime, we have rad(a * b * c) = rad(a) * rad(b) * rad(c).
# - Instead of trying all 'a' values in the range [1, c), we only try promising 'a' values such that rad(a) * rad(c) < c.
#   If we try 'a' values in ascending order of rad(a), then we can stop the search early and not examine many values of 'a'.
def compute():
	LIMIT = 120000
	
	# Modification of the sieve of Eratosthenes
	rads = [0] + [1] * (LIMIT - 1)
	for i in range(2, len(rads)):
		if rads[i] == 1:
			for j in range(i, len(rads), i):
				rads[j] *= i
	
	sortedrads = sorted((rad, n) for (n, rad) in enumerate(rads))
	sortedrads = sortedrads[1 : ]  # Get rid of the (0, 0) entry
	
	ans = 0
	for c in range(2, LIMIT):
		for (rad, a) in sortedrads:
			rad *= rads[c]
			if rad >= c:
				break
			b = c - a
			if a < b and rad * rads[b] < c and math.gcd(a, b) == 1:
				ans += c
	return str(ans)


if __name__ == "__main__":
	print(compute())
