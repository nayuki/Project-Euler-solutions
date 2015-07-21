# 
# Solution to Project Euler problem 31
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


ways = [0] * 201
ways[0] = 1
for coin in [1, 2, 5, 10, 20, 50, 100, 200]:
	for i in range(len(ways) - coin):
		ways[i + coin] += ways[i]
print(ways[-1])
