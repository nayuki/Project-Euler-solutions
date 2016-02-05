# 
# Solution to Project Euler problem 315
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	isprime = eulerlib.list_primality(20000000)
	ans = sum(sam_transitions_minus_max_transitions(i) for (i, p) in enumerate(isprime) if i >= 10000000 and p)
	return str(ans)


def sam_transitions_minus_max_transitions(n):
	samtrans = 0
	maxtrans = 0
	segmentstate = 0
	while True:
		newstate = number_to_segments(n)
		if newstate == segmentstate:
			break
		maxtrans += bin(newstate ^ segmentstate).count("1")
		segmentstate = newstate
		samtrans += 2 * bin(newstate).count("1")
		n = digit_sum(n)
	maxtrans += bin(segmentstate).count("1")
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
DECIMAL_DIGIT_TO_SEGMENT = [0x77, 0x12, 0x5D, 0x5B, 0x3A, 0x6B, 0x6F, 0x72, 0x7F, 0x7B]


if __name__ == "__main__":
	print(compute())
