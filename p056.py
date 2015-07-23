# 
# Solution to Project Euler problem 56
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = 0
	for a in range(100):
		for b in range(100):
			ans = max(sum([int(c) for c in str(a**b)]), ans)
	return str(ans)


if __name__ == "__main__":
	print(compute())
