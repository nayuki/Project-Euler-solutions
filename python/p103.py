# 
# Solution to Project Euler problem 103
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	TARGET_SIZE = 7
	for sum in itertools.count():
		set = SpecialSumSet.make_set(TARGET_SIZE, sum)
		if set is not None:
			return "".join(map(str, set.values))


class SpecialSumSet(object):
	
	@staticmethod
	def make_set(targetsize, maximumsum):
		return SpecialSumSet._make_set(SpecialSumSet([], [True], [0], [0]), targetsize, maximumsum, 1)
	
	
	@staticmethod
	def _make_set(set, sizeremain, sumremain, startval):
		if sizeremain == 0:
			return set
		if sizeremain >= 2 and startval * sizeremain >= sumremain:
			return None
		
		endval = sumremain
		if len(set.values) >= 2:
			endval = min(set.values[0] + set.values[1], endval)
		for val in range(startval, endval + 1):
			temp = set.add(val)
			if temp is None:
				continue
			temp = SpecialSumSet._make_set(temp, sizeremain - 1, sumremain - val, val + 1)
			if temp is not None:
				return temp
		return None
	
	
	def __init__(self, vals, sumposb, minsum, maxsum):
		self.values = vals
		self.sumpossible = sumposb
		self.minimumsum = minsum
		self.maximumsum = maxsum
	
	
	def add(self, val):
		if val <= 0:
			raise ValueError()
		size = len(self.values)
		if size >= 1 and val <= self.values[-1]:
			raise ValueError()
		
		posb = self.sumpossible
		if any((posb[i] and posb[i - val]) for i in range(val, len(posb))):
			return None
		
		newsize = size + 1
		newmin = self.minimumsum + [+INFINITY]
		newmax = self.maximumsum + [-INFINITY]
		for i in reversed(range(1, newsize + 1)):
			newmin[i] = min(newmin[i - 1] + val, newmin[i])
			newmax[i] = max(newmax[i - 1] + val, newmax[i])
		if any((newmax[i] >= newmin[i + 1]) for i in range(newsize)):
			return None
		
		newposb = posb + [False] * val
		for i in reversed(range(val, len(newposb))):
			newposb[i] |= newposb[i - val]
		return SpecialSumSet(self.values + [val], newposb, newmin, newmax)


INFINITY = 10**10


if __name__ == "__main__":
	print(compute())
