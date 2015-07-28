# 
# Solution to Project Euler problem 123
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Using the result from Project Euler #120, we know that
# (a-1)^n + (a+1)^n mod a^2 = if n is even then 2 else 2an.
# Since 2 is tiny, we can skip the n is even case.
# a is the n'th (1-based) prime number, so a > n. In fact for n >= 5,
# we have a > 2n, so we can take 2an directly without moduloing it by a^2.
def compute():
	# Sieve of Eratosthenes
	isprime = [True] * 1000001
	isprime[0] = isprime[1] = False
	for i in range(len(isprime)):
		if isprime[i]:
			for j in range(i * i, len(isprime), i):
				isprime[j] = False
	primes = [n for (n, x) in enumerate(isprime) if x]
	
	for n in range(5, len(primes), 2):
		rem = n * primes[n - 1] * 2
		if rem > 10000000000:
			return str(n)


if __name__ == "__main__":
	print(compute())
