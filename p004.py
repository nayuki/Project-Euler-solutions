# 
# Solution to Project Euler problem 4
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	max_palindrome = 0
	for i in range(100, 1000):
		for j in range(100, 1000):
			k = i * j
			s = str(k)
			if s == s[::-1] and k > max_palindrome:
				max_palindrome = k
	return str(max_palindrome)


if __name__ == "__main__":
	print(compute())
