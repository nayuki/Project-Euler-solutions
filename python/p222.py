# 
# Solution to Project Euler problem 222
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, math


def compute():
	NUM_SPHERES = 21
	
	
	sphereradii = [(i + 30) * 1000 for i in range(NUM_SPHERES)]  # In micrometres
	minlength = [[None] * (2**NUM_SPHERES) for _ in range(NUM_SPHERES)]
	
	# minlength[i][j] is the minimum achievable length for fitting a set of spheres in a cylindrical tube
	# of radius 50000 micrometres, where the sphere of radius sphereradii[i] is at the left end,
	# the bit vector j represents the set of spheres, and i must be in the set denoted by j.
	# (In the integer j, bit k denotes whether the sphere of radius sphereradii[k] is in the set or not.)
	# The right-side length of the rightmost sphere is included, the length of the distance between spheres
	# (arranged in an optimal way) is included, but the left-side length of the leftmost sphere is excluded.
	# 
	# For example, minlength[3][0x819] is the minimum length of fitting the set of spheres with radii
	# {30000, 33000, 34000, 41000} micrometres, where the leftmost sphere has radius 33000
	# (and this value is discounted from the total length).
	def find_minimum_length(currentsphereindex, setofspheres):
		if setofspheres & (1 << currentsphereindex) == 0:
			raise ValueError()
		
		# Memoization
		if minlength[currentsphereindex][setofspheres] is None:
			if eulerlib.popcount(setofspheres) == 1:
				result = sphereradii[currentsphereindex]  # This sphere is rightmost
			else:
				result = float("inf")
				newsetofspheres = setofspheres ^ (1 << currentsphereindex)
				for i in range(NUM_SPHERES):  # i is the index of the next sphere
					if newsetofspheres & (1 << i) == 0:
						continue
					# The sqrt() here is what makes the entire computation not guaranteed to be accurate
					temp = math.sqrt((sphereradii[i] + sphereradii[currentsphereindex] - 50000) * 200000)
					temp += find_minimum_length(i, newsetofspheres)
					result = min(temp, result)
			minlength[currentsphereindex][setofspheres] = result
		return minlength[currentsphereindex][setofspheres]
	
	
	ans = min((find_minimum_length(i, (1 << NUM_SPHERES) - 1) + sphereradii[i])
		for i in range(NUM_SPHERES))
	return str(int(round(ans)))


if __name__ == "__main__":
	print(compute())
