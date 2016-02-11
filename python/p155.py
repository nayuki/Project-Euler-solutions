# 
# Solution to Project Euler problem 155
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import fractions


# Warning: Running this solution requires about 1 GiB of memory
def compute():
	SIZE = 18
	# possible[i] holds all the possible capacitance values of a series/parallel
	# capacitor network that uses exactly i capacitors of 60 uF each
	possible = []
	all = set()  # Union of every possible[i]
	possible.append(set())
	possible.append(set([fractions.Fraction(60, 1)]))
	all.update(possible[1])
	for i in range(2, SIZE + 1):
		poss = set()
		for j in range(1, i // 2 + 1):
			for a in possible[j]:
				for b in possible[i - j]:
					sum = a + b
					poss.add(sum)  # Parallel
					poss.add(a * b / sum)  # Series
		possible.append(poss)
		all.update(poss)
	return str(len(all))


if __name__ == "__main__":
	print(compute())
