# 
# Solution to Project Euler problem 88
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# minSumProduct[k] is the smallest positive integers that can be written as both a sum and a product of the same collection of k positive integers.
# For example, minSumProduct[3] = 6 because 6 = 1 + 2 + 3 = 1 * 2 * 3, and this is the minimum possible number for 3 terms.
# 
# For all k >= 2:
# - minSumProduct[k] > k because 1 + ... + 1 (with k terms) = k, which is the minimum sum of k positive integers,
#   but the product is 1 which is unequal to k, so k is not a valid solution.
# - minSumProduct[k] <= 2k because 1 + ... + 1 + 2 + k (with k terms in total) = (k - 2) + 2 + k = 2k. The product is 2k, which equals the sum.
#   Since this is one achievable solution, the minimum solution must be no larger than this.
# - Aside: minSumProduct[k] is not a prime number. Suppose minSumProduct[k] = p, where p is prime. Then p can only be factorized as p, p * 1, p * 1 * 1, etc.
#   So whenever the factorization has more than one term, the sum exceeds p, which makes it unequal to the product.
# 
# Therefore we need to consider all numbers from 2 to LIMIT*2 and factorize them in all possible ways to find all the relevant solutions.
def compute():
	LIMIT = 12000
	minsumproduct = [None] * (LIMIT + 1)
	
	# Calculates all factorizations of the integer n >= 2 and updates smaller solutions into minSumProduct.
	# For example, 12 can be factorized as follows - and duplicates are eliminated by finding only non-increasing sequences of factors:
	# - 12 = 12. (1 term)
	# - 12 = 6 * 2 * 1 * 1 * 1 * 1 = 6 + 2 + 1 + 1 + 1 + 1. (6 terms)
	# - 12 = 4 * 3 * 1 * 1 * 1 * 1 * 1 = 4 + 3 + 1 + 1 + 1 + 1 + 1. (7 terms)
	# - 12 = 3 * 2 * 2 * 1 * 1 * 1 * 1 * 1 = 3 + 2 + 2 + 1 + 1 + 1 + 1 + 1. (8 terms)
	def factorize(n, remain, maxfactor, sum, terms):
		if remain == 1:
			if sum > n:  # Without using factors of 1, the sum never exceeds the product
				raise AssertionError()
			terms += n - sum
			if terms <= LIMIT and (minsumproduct[terms] is None or n < minsumproduct[terms]):
				minsumproduct[terms] = n
		else:
			# Note: maxfactor <= remain
			for i in range(2, maxfactor + 1):
				if remain % i == 0:
					factor = i
					factorize(n, remain // factor, min(factor, maxfactor), sum + factor, terms + 1)
	
	for i in range(2, LIMIT * 2 + 1):
		factorize(i, i, i, 0, 0)
	
	# Eliminate duplicates and compute sum
	ans = sum(set(minsumproduct[2 : ]))
	return str(ans)


if __name__ == "__main__":
	print(compute())
