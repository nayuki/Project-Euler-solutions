# 
# Solution to Project Euler problem 179
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	divisors = [2] * (10**7 + 1)  # Invalid for indexes 0 and 1
	for i in range(2, (len(divisors) + 1) // 2):
		for j in range(i * 2, len(divisors), i):
			divisors[j] += 1
	
	ans = sum((1 if divisors[i] == divisors[i + 1] else 0) for i in range(2, len(divisors) - 1))
	return str(ans)


if __name__ == "__main__":
	print(compute())
