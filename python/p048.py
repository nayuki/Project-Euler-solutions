# 
# Solution to Project Euler problem 48
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	MOD = 10**10
	ans = sum(pow(i, i, MOD) for i in range(1, 1001)) % MOD
	return str(ans)


if __name__ == "__main__":
	print(compute())
