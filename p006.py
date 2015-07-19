# 
# Solution to Project Euler problem 6
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


sum = 0
sum2 = 0
for i in range(1, 101):
	sum += i
	sum2 += i**2
print(sum**2 - sum2)
