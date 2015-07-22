# 
# Solution to Project Euler problem 9
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	LIMIT = 1000
	for a in range(1, LIMIT + 1):
		for b in range(a, LIMIT + 1):
			c = LIMIT - a - b
			if c >= b:
				# Now a <= b <= c
				if a**2 + b**2 == c**2:
					return str(a * b * c)


if __name__ == "__main__":
	print(compute())
