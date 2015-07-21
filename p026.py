# 
# Solution to Project Euler problem 26
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def reciprocal_cycle_len(n):
	seen = {}
	x = 1
	i = 0
	while x not in seen:
		seen[x] = i
		x = x * 10 % n
		i += 1
	return i - seen[x]


ans = 0
maxcyclelen = 0
for i in range(1, 1000):
	cylen = reciprocal_cycle_len(i)
	if cylen > maxcyclelen:
		ans = i
		maxcyclelen = cylen
print(ans)
