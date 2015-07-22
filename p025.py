# 
# Solution to Project Euler problem 25
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	a = 1
	b = 1
	i = 1
	while len(str(a)) != 1000:
		a, b = b, a + b
		i += 1
	return str(i)


if __name__ == "__main__":
	print(compute())
