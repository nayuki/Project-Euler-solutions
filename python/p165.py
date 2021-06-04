# 
# Solution to Project Euler problem 165
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import fractions


def compute():
	rand = BbsRandom()
	lines = [LineSegment(rand) for _ in range(NUM_LINE_SEGMENTS)]
	
	trueintersections = set()
	for (i, seg0) in enumerate(lines):
		for seg1 in lines[i + 1 : ]:
			x0 = seg0.x0;  y0 = seg0.y0
			x1 = seg0.x1;  y1 = seg0.y1
			x2 = seg1.x0;  y2 = seg1.y0
			x3 = seg1.x1;  y3 = seg1.y1
			
			# https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line_segment
			denom = (x0 - x1) * (y2 - y3) - (x2 - x3) * (y0 - y1)
			if denom == 0:
				# There is no unique intersection point between the two infinite lines. This is equivalent to
				# {one or both line segments being just a point, or both line segments being parallel
				# (regardless of whether or not they lie on the same infinite line)}.
				continue
			numer0 = (x0 - x2) * (y2 - y3) - (x2 - x3) * (y0 - y2)
			numer1 = (x1 - x0) * (y0 - y2) - (x0 - x2) * (y1 - y0)
			
			t0 = fractions.Fraction(numer0, denom)
			t1 = fractions.Fraction(numer1, denom)
			if 0 < t0 < 1 and 0 < t1 < 1:
				trueintersections.add((x0 + t0 * (x1 - x0), y0 + t0 * (y1 - y0)))
	
	return str(len(trueintersections))


NUM_LINE_SEGMENTS = 5000



# Blum Blum Shub generator
class BbsRandom:
	
	def __init__(self):
		self.state = 290797
	
	
	def next(self):
		self.state *= self.state
		self.state %= 50515093
		return self.state % 500



class LineSegment:
	
	def __init__(self, r):
		self.x0 = r.next()
		self.y0 = r.next()
		self.x1 = r.next()
		self.y1 = r.next()



if __name__ == "__main__":
	print(compute())
