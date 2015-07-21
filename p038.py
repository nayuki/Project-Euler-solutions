# 
# Solution to Project Euler problem 38
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


ans = 0
for n in range(2, 10):
	for i in range(1, 10**(9 // n)):
		s = ""
		for j in range(1, n + 1):
			s += str(i * j)
		if "".join(sorted(s)) == "123456789":
			ans = max(int(s), ans)
print(ans)
