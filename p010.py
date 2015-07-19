# 
# Solution to Project Euler problem 10
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Sieve of Eratosthenes
ans = 0
isprime = [True] * 2000001
for i in range(2, len(isprime)):
	if isprime[i]:
		ans += i
		for j in range(i * i, len(isprime), i):
			isprime[j] = False
print(ans)
