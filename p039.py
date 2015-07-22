# 
# Solution to Project Euler problem 39
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = 0
	maxsoln = 0
	for p in range(1, 1001):
		soln = count_solutions(p)
		if soln > maxsoln:
			ans = p
			maxsoln = soln
	return str(ans)


def count_solutions(p):
	result = 0
	for a in range(1, p + 1):
		for b in range(a, (p - a) // 2 + 1):
			c = p - a - b  # c >= b
			if a * a + b * b == c * c:
				result += 1
	return result


if __name__ == "__main__":
	print(compute())
