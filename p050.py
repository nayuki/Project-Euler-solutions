# 
# Solution to Project Euler problem 50
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	# Sieve of Eratosthenes
	isprime = [True] * 1000000
	isprime[0] = isprime[1] = False
	for i in range(len(isprime)):
		if isprime[i]:
			for j in range(i * i, len(isprime), i):
				isprime[j] = False
	primes = [n for (n, x) in enumerate(isprime) if x]
	
	ans = 0
	consecutive = 0
	for i in range(len(primes)):
		sum = primes[i]
		consec = 1
		for j in range(i + 1, len(primes)):
			sum += primes[j]
			consec += 1
			if sum >= len(isprime):
				break
			if isprime[sum] and consec > consecutive:
				ans = sum
				consecutive = consec
	return str(ans)


if __name__ == "__main__":
	print(compute())
