# 
# Solution to Project Euler problem 80
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	ans = 0
	for i in range(100):
		if eulerlib.sqrt(i)**2 != i:
			temp = eulerlib.sqrt(i * 10**200)
			ans += sum(int(c) for c in str(temp)[ : 100])
	return str(ans)


if __name__ == "__main__":
	print(compute())
