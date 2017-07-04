# 
# Solution to Project Euler problem 315
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	isprime = eulerlib.list_primality(20000000)
	ans = sum(sam_transitions_minus_max_transitions(i)
		for (i, p) in enumerate(isprime)
		if i >= 10000000 and p)
	return str(ans)


def sam_transitions_minus_max_transitions(n):
	samtrans = 0
	maxtrans = 0
	segmentstate = 0
	while True:
		newstate = number_to_segments(n)
		if newstate == segmentstate:
			break
		maxtrans += eulerlib.popcount(newstate ^ segmentstate)
		segmentstate = newstate
		samtrans += 2 * eulerlib.popcount(newstate)
		n = digit_sum(n)
	maxtrans += eulerlib.popcount(segmentstate)
	return samtrans - maxtrans


def number_to_segments(n):
	if n < 0:
		raise ValueError()
	result = 0
	i = 0
	while True:
		result |= DECIMAL_DIGIT_TO_SEGMENT[n % 10] << (i * 7)
		n //= 10
		i += 1
		if n == 0:
			return result


# Also known as digital root.
def digit_sum(n):
	if n < 0:
		raise ValueError()
	result = 0
	while n != 0:
		result += n % 10
		n //= 10
	return result


# Mapping of [0, 10) -> [0x00, 0x7F); each output fits in 7 bits.
DECIMAL_DIGIT_TO_SEGMENT = [0b1110111, 0b0010010, 0b1011101, 0b1011011, 0b0111010, 0b1101011, 0b1101111, 0b1110010, 0b1111111, 0b1111011]


if __name__ == "__main__":
	print(compute())
