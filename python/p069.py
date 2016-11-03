# 
# Solution to Project Euler problem 69
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


def compute():
	totients = eulerlib.list_totients(10**6)
	maxnumer = 0
	maxdenom = 1
	for (i, tot) in itertools.islice(enumerate(totients), 2, None):
		if i * maxdenom > maxnumer * tot:
			maxnumer = i
			maxdenom = totients[i]
	return str(maxnumer)


if __name__ == "__main__":
	print(compute())
