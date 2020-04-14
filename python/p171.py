# 
# Solution to Project Euler problem 171
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


# The key insight is to use dynamic programming to build up the answer one digit at a time.
# 
# Let Num(n, s) denote the set of numbers of length n (with leading zeros) whose squared digits sum to s.
# For example, Num(2, 25) = {05, 34, 43, 50}.
# Then for any particular n and s, we know that Num(n, s) = union of
#   (prepend 0 to each of Num(n-1, s - 0*0)),
#   (prepend 1 to each of Num(n-1, s - 1*1)),
#   ...,
#   (prepend 9 to each of Num(n-1, s - 9*9)).
# 
# However, keeping track of these sets of numbers explicitly is just as slow as iterating over
# all the numbers by brute force. So instead, we only store the sums and counts of these sets,
# and these two pieces of information are enough to determine the final answer.
# (Furthermore, these can be reduced by the modulus.)
def compute():
	LENGTH = 20
	BASE = 10
	MODULUS = 10**9
	
	# Maximum possible squared digit sum (for 99...99)
	MAX_SQR_DIGIT_SUM = (BASE - 1)**2 * LENGTH
	
	# sqsum[n][s] is the sum of all length-n numbers with a square digit sum of s, modulo MODULUS
	# count[n][s] is the count of all length-n numbers with a square digit sum of s, modulo MODULUS
	sqsum = []
	count = []
	
	for i in range(LENGTH + 1):
		sqsum.append([0] * (MAX_SQR_DIGIT_SUM + 1))
		count.append([0] * (MAX_SQR_DIGIT_SUM + 1))
		if i == 0:
			count[0][0] = 1
		else:
			for j in range(BASE):
				for k in itertools.count():
					index = k + j**2
					if index > MAX_SQR_DIGIT_SUM:
						break
					sqsum[i][index] = (sqsum[i][index] + sqsum[i - 1][k] + pow(BASE, i - 1, MODULUS) * j * count[i - 1][k]) % MODULUS
					count[i][index] = (count[i][index] + count[i - 1][k]) % MODULUS
	
	ans = sum(sqsum[LENGTH][i**2] for i in range(1, eulerlib.sqrt(MAX_SQR_DIGIT_SUM)))
	return f"{ans%MODULUS:09}"


if __name__ == "__main__":
	print(compute())
