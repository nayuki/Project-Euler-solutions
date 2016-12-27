# 
# Solution to Project Euler problem 215
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	WIDTH = 32
	HEIGHT = 10
	crackpositions = []
	
	def get_crack_positions(cracks, position):
		if position < 0:
			raise ValueError()
		elif position < WIDTH:
			for i in (2, 3):
				cracks.append(position + i)
				get_crack_positions(cracks, position + i)
				cracks.pop()
		elif position == WIDTH:
			crackpositions.append(frozenset(cracks[ : -1]))
		else:  # position > WIDTH
			return
	
	get_crack_positions([], 0)
	
	noncrackindices = [
		[i for (i, cp1) in enumerate(crackpositions) if cp0.isdisjoint(cp1)]
		for cp0 in crackpositions]
	
	ways = [1] * len(crackpositions)
	for i in range(1, HEIGHT):
		newways = [sum(ways[k] for k in nci) for nci in noncrackindices]
		ways = newways
	
	ans = sum(ways)
	return str(ans)


if __name__ == "__main__":
	print(compute())
