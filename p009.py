# 
# Solution to Project Euler problem 9
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


LIMIT = 1000
for a in range(1, LIMIT + 1):
	for b in range(a, LIMIT + 1):
		c = LIMIT - a - b
		if c >= b:
			# Now a <= b <= c
			if a**2 + b**2 == c**2:
				print(a * b * c)
