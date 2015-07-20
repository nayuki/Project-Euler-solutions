# 
# Solution to Project Euler problem 21
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


divisorsum = [0] * 10000
for i in range(1, len(divisorsum)):
	for j in range(i * 2, len(divisorsum), i):
		divisorsum[j] += i

ans = 0
for i in range(1, len(divisorsum)):
	j = divisorsum[i]
	if j != i and j < len(divisorsum) and divisorsum[j] == i:
		ans += i
print(ans)
