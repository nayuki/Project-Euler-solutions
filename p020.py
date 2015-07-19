# 
# Solution to Project Euler problem 20
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def factorial(n):
	result = 1
	for i in range(1, n + 1):
		result *= i
	return result


n = factorial(100)
ans = sum(map(int, str(n)))
print(ans)
