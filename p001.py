# 
# Solution to Project Euler problem 1
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


ans = sum([x for x in range(1000) if (x % 3 == 0 or x % 5 == 0)])
print(ans)
