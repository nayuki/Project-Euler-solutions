# 
# Solution to Project Euler problem 19
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import datetime


ans = 0
for y in range(1901, 2001):
	for m in range(1, 13):
		if datetime.date(y, m, 1).weekday() == 6:
			ans += 1
print(ans)
