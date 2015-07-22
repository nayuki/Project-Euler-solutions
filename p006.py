# 
# Solution to Project Euler problem 6
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	sum = 0
	sum2 = 0
	for i in range(1, 101):
		sum += i
		sum2 += i**2
	return str(sum**2 - sum2)


if __name__ == "__main__":
	print(compute())
