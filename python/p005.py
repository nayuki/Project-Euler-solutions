# 
# Solution to Project Euler problem 5
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	ans = 1
	for i in range(1, 21):
		ans *= i // eulerlib.gcd(i, ans)
	return str(ans)


if __name__ == "__main__":
	print(compute())
