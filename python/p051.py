# 
# Solution to Project Euler problem 51
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	isprime = eulerlib.list_primality(1000000)
	for i in range(len(isprime)):
		if not isprime[i]:
			continue
		
		n = [int(c) for c in str(i)]
		for mask in range(1 << len(n)):
			digits = do_mask(n, mask)
			count = 0
			for j in range(10):
				if digits[0] != 0 and isprime[to_number(digits)]:
					count += 1
				digits = add_mask(digits, mask)
			
			if count == 8:
				digits = do_mask(n, mask)
				for j in range(10):
					if digits[0] != 0 and isprime[to_number(digits)]:
						return str(to_number(digits))
					digits = add_mask(digits, mask)
	raise AssertionError("Not found")


def do_mask(digits, mask):
	return [d * ((~mask >> i) & 1) for (i, d) in enumerate(digits)]


def add_mask(digits, mask):
	return [d + ((mask >> i) & 1) for (i, d) in enumerate(digits)]


def to_number(digits):
	result = 0
	for d in digits:
		result = result * 10 + d
	return result


if __name__ == "__main__":
	print(compute())
