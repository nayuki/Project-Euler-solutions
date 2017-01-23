# 
# Solution to Project Euler problem 25
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


# Because the target number is relatively small, we simply compute each Fibonacci number starting
# from the beginning until we encounter one with exactly 1000 digits. The Fibonacci sequence grows
# exponentially with a base of about 1.618, so the numbers in base 10 will lengthen by one digit
# after every log10(1.618) ~= 4.78 steps on average. This means the answer is at index around 4780.
def compute():
	DIGITS = 1000
	prev = 1
	cur = 0
	for i in itertools.count():
		# At this point, prev = fibonacci(i - 1) and cur = fibonacci(i)
		if len(str(cur)) > DIGITS:
			raise RuntimeError("Not found")
		elif len(str(cur)) == DIGITS:
			return str(i)
		
		# Advance the Fibonacci sequence by one step
		prev, cur = cur, prev + cur


if __name__ == "__main__":
	print(compute())
