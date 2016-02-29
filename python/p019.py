# 
# Solution to Project Euler problem 19
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import datetime


def compute():
	ans = sum(1
		for y in range(1901, 2001)
		for m in range(1, 13)
		if datetime.date(y, m, 1).weekday() == 6)
	return str(ans)


if __name__ == "__main__":
	print(compute())
