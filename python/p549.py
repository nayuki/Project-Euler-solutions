# 
# Solution to Project Euler problem 549
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	LIMIT = 10**8
	
	smallestdivisiblefactorials = [0] * (LIMIT + 1)
	
	def set_multiples(k, val):
		for i in range(k, len(smallestdivisiblefactorials), k):
			smallestdivisiblefactorials[i] = max(val, smallestdivisiblefactorials[i])
	
	for i in range(2, len(smallestdivisiblefactorials)):
		if smallestdivisiblefactorials[i] == 0:
			# Now we know that i is prime
			power = 1
			for j in itertools.count(i, i):
				temp = j
				while temp % i == 0:
					power *= i
					set_multiples(power, j)
					temp //= i
				if power > LIMIT:
					break
	
	ans = sum(smallestdivisiblefactorials)
	return str(ans)


if __name__ == "__main__":
	print(compute())
