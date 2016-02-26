# 
# Solution to Project Euler problem 72
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
	
	ans = sum(itertools.islice(totients, 2, None))
	return str(ans)


if __name__ == "__main__":
	print(compute())
