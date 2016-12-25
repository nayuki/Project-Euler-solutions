# 
# Solution to Project Euler problem 346
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	LIMIT = 10**12
	
	strongrepunits = {1}
	for length in range(3, LIMIT.bit_length() + 1):
		for base in itertools.count(2):
			number = sum(base**i for i in range(length))
			if number >= LIMIT:
				break
			strongrepunits.add(number)
	
	ans = sum(strongrepunits)
	return str(ans)


if __name__ == "__main__":
	print(compute())
