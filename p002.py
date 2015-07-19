# 
# Solution to Project Euler problem 2
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


ans = 0
x = 1
y = 2
while x <= 4000000:
	if x % 2 == 0:
		ans += x
	x, y = y, x + y
print(ans)
