# 
# Solution to Project Euler problem 500
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, heapq


def compute():
	TARGET = 500500
	MODULUS = 500500507
	isprime = eulerlib.list_primality(7376507)  # 500500th (1-based) prime number
	
	queue = []
	nextprime = 2
	heapq.heappush(queue, (nextprime, nextprime))
	
	ans = 1
	for _ in range(TARGET):
		nextpower, prime = heapq.heappop(queue)
		ans *= nextpower
		ans %= MODULUS
		heapq.heappush(queue, (nextpower**2, prime))
		
		if prime == nextprime:
			nextprime += 1
			while not isprime[nextprime]:
				nextprime += 1
			heapq.heappush(queue, (nextprime, nextprime))
	
	return str(ans)


if __name__ == "__main__":
	print(compute())
