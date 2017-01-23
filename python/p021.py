# 
# Solution to Project Euler problem 21
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# We first compute a table of sum-of-proper-divisors, then we use it to test which numbers are amicable.
# This approach differs from the Java implementation because trying to directly compute
# the proper-divisor-sum of each number by brute force is unacceptably slow in Python.
def compute():
	# Compute sum of proper divisors for each number
	divisorsum = [0] * 10000
	for i in range(1, len(divisorsum)):
		for j in range(i * 2, len(divisorsum), i):
			divisorsum[j] += i
	
	# Find all amicable pairs within range
	ans = 0
	for i in range(1, len(divisorsum)):
		j = divisorsum[i]
		if j != i and j < len(divisorsum) and divisorsum[j] == i:
			ans += i
	return str(ans)


if __name__ == "__main__":
	print(compute())
