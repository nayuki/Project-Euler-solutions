# 
# Solution to Project Euler problem 36
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = 0
	for i in range(1000000):
		s = str(i)
		if s == s[::-1]:
			t = bin(i)[2 : ]
			if t == t[::-1]:
				ans += i
	return str(ans)


if __name__ == "__main__":
	print(compute())
