# 
# Solution to Project Euler problem 35
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


isprime = [True] * 1000000

def compute():
	# Sieve of Eratosthenes
	isprime[0] = isprime[1] = False
	for i in range(len(isprime)):
		if isprime[i]:
			for j in range(i * i, len(isprime), i):
				isprime[j] = False
	
	ans = 0
	for i in range(len(isprime)):
		if is_circular_prime(i):
			ans += 1
	return str(ans)


def is_circular_prime(n):
	s = str(n)
	for i in range(len(s)):
		if not isprime[int(s[i : ] + s[ : i])]:
			return False
	return True


if __name__ == "__main__":
	print(compute())
