# 
# Solution to Project Euler problem 1
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Computers are fast, so we can implement this solution directly without any clever math.
def compute():
	ans = sum(set(range(0, 1000, 3)).union(set(range(0, 1000, 5))))
	return str(ans)


if __name__ == "__main__":
	print(compute())
