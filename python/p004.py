# 
# Solution to Project Euler problem 4
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Computers are fast, so we can implement this solution directly without any clever math.
def compute():
	ans = 0
	stop = 100
	for i in range(1000, 100, -1):
		for j in range(1000, stop, -1):
			k = i * j
			s = str(k)
			if s == s[::-1] and k > ans:
				ans = k
				stop = j
	return str(ans)


if __name__ == "__main__":
	print(compute())
