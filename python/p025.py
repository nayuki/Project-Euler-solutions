# 
# Solution to Project Euler problem 25
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Because the target number is relatively small, we simply compute each Fibonacci number starting
# from the beginning until we encounter one with exactly 1000 digits. The Fibonacci sequence grows
# exponentially with a base of about 1.618, so the numbers in base 10 will lengthen by one digit
# after every log10(1.618) ~= 4.78 steps on average. This means the answer is at index around 4780.
def compute():
	a = 1
	b = 1
	i = 1
	while len(str(a)) != 1000:
		# Loop invariant: At this point, a == fib(i) and b == fib(i+1)
		a, b = b, a + b
		i += 1
	return str(i)


if __name__ == "__main__":
	print(compute())
