# 
# Solution to Project Euler problem 127
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions, itertools


def compute():
	LIMIT = 120000
	smallestprimefactor = eulerlib.list_smallest_prime_factors(LIMIT - 1)
	
	rads = [0]
	for i in range(1, LIMIT):
		n = i
		rad = 1
		while n > 1:
			p = smallestprimefactor[n]
			while True:
				n //= p
				if n % p != 0:
					break
			rad *= p
		rads.append(rad)
	
	# - gcd(a,b) = gcd(a,c) = gcd(b,c), so we only need to compute one of them.
	# - Since {a, b, c} are mutually coprime, rad(a * b * c) = rad(a) * rad(b) * rad(c).
	# - rad(a)*rad(b)*rad(c) < c implies rad(a)*rad(b)*rad(c) <= c-1 implies rad(a)*rad(b) <= floor((c-1)/rad(c)).
	sum = 0
	for c in range(2, LIMIT):
		thres = (c - 1) // rads[c]
		for a in itertools.count(1):
			b = c - a
			if b <= a:
				break
			# The first two conditions are just optional optimizations
			if rads[a] <= thres and rads[b] <= thres and rads[a] * rads[b] <= thres and fractions.gcd(a, b) == 1:
				sum += c
	return str(sum)


if __name__ == "__main__":
	print(compute())
