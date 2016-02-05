# 
# Solution to Project Euler problem 124
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	# Modification of the Sieve of Eratosthenes
	rads = [1] * 100001
	for i in range(2, len(rads)):
		if rads[i] == 1:
			for j in range(i, len(rads), i):
				rads[j] *= i
	
	data = [(rads[i], i) for i in range(len(rads))]
	data.sort()
	return str(data[10000][1])


if __name__ == "__main__":
	print(compute())
