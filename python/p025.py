# 
# Solution to Project Euler problem 25
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 
#https://github.com/brayo-pip/Project-Euler-solutions
from math import sqrt,log10

def compute():
	"""
	calculates the first fibonacci number to exceed
	1000 digits using logs.
	runs in constant time
	"""
	golden_ratio=0.5*(1+sqrt(5))

	log_ratio=log10(golden_ratio)

	print(int(1000/log_ratio-2))

if __name__ == "__main__":
	print(compute())
