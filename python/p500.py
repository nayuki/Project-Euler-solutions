# 
# Solution to Project Euler problem 500
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, heapq


def compute():
	POWER = 500500
	MODULUS = 500500507
	isprime = eulerlib.list_primality(7376507)  # 500500th (1-based) prime number
	
	queue = []
	largestprime = 2
	heapq.heappush(queue, (largestprime, largestprime))
	
	ans = 1
	for _ in range(POWER):
		nextpower, prime = heapq.heappop(queue)
		ans *= nextpower
		ans %= MODULUS
		heapq.heappush(queue, (nextpower**2, prime))
		
		if prime == largestprime:
			largestprime += 1
			while not isprime[largestprime]:
				largestprime += 1
			heapq.heappush(queue, (largestprime, largestprime))
	
	return str(ans)


if __name__ == "__main__":
	print(compute())
