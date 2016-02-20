# 
# Solution to Project Euler problem 323
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


# Suppose that n 32-bit integers have been OR'd together.
# For any arbitrary bit:
#   The probability that it is 0 is 1/2^n.
#   The probability that it is 1 is 1 - 1/2^n.
# Thus for the entire number:
#   The probability that all bits are 1 is (1 - 1/2^n)^32.
#     This is the cumulative distribution function that we want.
#   The probability that some bit is 0 is 1 - (1 - 1/2^n)^32.
# 
# The probability density function is simply pdf(n) = cdf(n) - cdf(n-1).
# So the expected value of the index where the number becomes all 1's is
# sum(n * pdf(n) for n = 0 to infinity).
def compute():
	# Computes an approximate answer using floating-point, not guaranteed to be correct.
	# However, the Mathematica version of the solution is exact.
	def cdf(n):
		return (1 - 0.5**n)**32 if n >= 0 else 0.0
	
	ans = 0.0
	for n in itertools.count(1):
		p = cdf(n) - cdf(n - 1)
		if p < 1e-20:  # Truncate the series by ignoring insignificant contributions to the sum
			break
		ans += n * p
	return "{:.10f}".format(ans)


if __name__ == "__main__":
	print(compute())
