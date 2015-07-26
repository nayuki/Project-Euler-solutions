# 
# Solution to Project Euler problem 4
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


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
