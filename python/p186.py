# 
# Solution to Project Euler problem 186
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ds = DisjointSet(1000000)
	rand = LfgRandom()
	ans = 0
	while ds.size(524287) < 990000:
		caller = rand.next()
		callee = rand.next()
		if caller != callee:
			ds.union(caller, callee)
			ans += 1
	return str(ans)


class DisjointSet:
	
	def __init__(self, size):
		self.nodes = [DisjointSet.Node() for _ in range(size)]
	
	def _find(self, i):
		return self.nodes[i].find()
	
	def union(self, i, j):
		x = self._find(i)
		y = self._find(j)
		if x == y:
			return
		if x.rank == y.rank:
			x.rank += 1
		elif x.rank < y.rank:
			x, y = y, x
		y.parent = x
		x.size += y.size
		y.size = 0
	
	def size(self, i):
		return self._find(i).size
	
	
	class Node:
		def __init__(self):
			self.parent = self
			self.rank = 0
			self.size = 1
		
		def find(self):
			if self.parent != self:
				self.parent = self.parent.find()  # Path compression
			return self.parent


class LfgRandom:
	
	def __init__(self):
		self.k = 1
		self.history = [0] * 55
		self.index = 0
	
	def next(self):
		k = self.k
		if k <= 55:
			result = (100003 - 200003 * k + 300007 * k * k * k) % 1000000
			self.k += 1
		else:
			result = (self.history[-24] + self.history[-55]) % 1000000
		del self.history[0]
		self.history.append(result)
		return result


if __name__ == "__main__":
	print(compute())
