# 
# Solution to Project Euler problem 6
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	x = sum(i for i in range(1, 101))**2
	y = sum(i**2 for i in range(1, 101))
	return str(x - y)


if __name__ == "__main__":
	print(compute())
