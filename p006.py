# 
# Solution to Project Euler problem 6
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	s  = sum([i    for i in range(1, 101)])
	s2 = sum([i**2 for i in range(1, 101)])
	return str(s**2 - s2)


if __name__ == "__main__":
	print(compute())
