# 
# Solution to Project Euler problem 14
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = max((i for i in range(1, 1000000)), key=collatz_chain_length)
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
