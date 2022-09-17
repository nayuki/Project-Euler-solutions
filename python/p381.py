# 
# Solution to Project Euler problem 381
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	# Note about the mathematical simplification:
	# (p-5)! + (p-4)! + (p-3)! + (p-2)! + (p-1)!
	# = (p-5)! * (1 + (p-4) + (p-4)(p-3) + (p-4)(p-3)(p-2) + (p-4)(p-3)(p-2)(p-1))
	# = (p-5)! * (1 + (-4) + (-4)(-3) + (-4)(-3)(-2) + (-4)(-3)(-2)(-1))
	# = (p-5)! * (1 + -4 + 12 + -24 + 24)
	# = (p-5)! * 9
	# = (p-1)! / ((p-1)(p-2)(p-3)(p-4)) * 9
	# = (p-1)! / ((-1)(-2)(-3)(-4)) * 9
	# = (p-1)! / 24 * 9
	# = (p-1)! * (3 * 3) / (3 * 8)
	# = (p-1)! * 3 / 8
	# = -1 * 3 / 8  (by Wilson's theorem)
	# = -3/8 mod p.
	# Every part of the equation is modulo a prime p > 4.
	def s(p):
		return (p - 3) * pow(8, -1, p) % p
	
	ans = sum(s(p) for p in eulerlib.prime_generator(10**8) if p >= 5)
	return str(ans)


if __name__ == "__main__":
	print(compute())
