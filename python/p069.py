# 
# Solution to Project Euler problem 69
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools, sys
if sys.version_info.major == 2:
	range = xrange


def compute():
	totients = list(range(1000001))
	for i in range(2, len(totients)):
		if totients[i] == i:  # i is prime
			for j in range(i, len(totients), i):
				totients[j] = totients[j] // i * (i - 1)
	
	maxnumer = 0
	maxdenom = 1
	for (i, tot) in itertools.islice(enumerate(totients), 2, None):
		if i * maxdenom > maxnumer * tot:
			maxnumer = i
			maxdenom = totients[i]
	return str(maxnumer)


if __name__ == "__main__":
	print(compute())
