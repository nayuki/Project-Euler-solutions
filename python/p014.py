# 
# Solution to Project Euler problem 14
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import sys
if sys.version_info.major == 2:
	range = xrange


# We compute the Collatz chain length for every integer in the range according to the iteration rule.
# Also, we cache the Collatz value for all integer arguments to speed up the computation.
def compute():
	ans = max(range(1, 1000000), key=collatz_chain_length)
	return str(ans)


collatz_cache = {1: 1}

def collatz_chain_length(x):
	if x not in collatz_cache:
		if x % 2 == 0:
			y = x // 2
		else:
			y = x * 3 + 1
		collatz_cache[x] = collatz_chain_length(y) + 1
	return collatz_cache[x]


if __name__ == "__main__":
	print(compute())
