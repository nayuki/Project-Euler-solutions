# 
# Solution to Project Euler problem 101
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools
from fractions import Fraction


# The generating function u(n) is a polynomial of degree 10.
# OP(k, n) is a polynomial of degree at most k-1, which can be obtained
# by the Lagrange interpolating polynomial (or other methods).
# Any polynomial P(n) of degree k has at most k roots (i.e. points where P(n) = 0).
# The zero polynomial Z(n) = 0 has negative infinite degree, and has roots everywhere.
# Now, let E(n) = u(n) - OP(k, n), which is also a polynomial.
# 
# If k <= 10, then OP(k, n) has degree less than k <= 10, so E(n) has degree 10. So E(n) has at most 10 roots.
# By construction, OP(k, n) = u(n) for n = 1, 2, ..., k, thus E(n) already has k roots at {1, 2, ..., k}.
# E(n) has at most 10 - k roots remaining, hence among the 11 - k values {k+1, k+2, ..., 11},
# there must be an n where E(n) != 0 (i.e. an incorrect term where OP(k, n) != u(n)).
# 
# If k > 10, E(n) has k roots at {1, 2, ..., k}, and possibly others.
# So either E(n) has degree at least k, or it's the zero polynomial.
# Now, u(n) has degree 10 and OP(k, n) has degree at most k-1,
# so their difference E(n) has at most degree max(10, k-1) = k-1.
# This means E(n) does not have degree k, so it is the zero polynomial.
# Hence u(n) = OP(k, n), and there are no incorrect terms.
# 
# In conclusion, BOPs exist for and only for 1 <= k <= 10. For each k in that range,
# the first incorrect term (FIT) of OP(k, n) exists for some n in {k+1, k+2, ..., 11}.
DEGREE = 10
def compute():
	ans = Fraction(0, 1)
	for k in range(1, DEGREE + 1):
		for n in itertools.count(k + 1):
			if n == DEGREE + 2:
				raise AssertionError()
			reference = Fraction(generating_function(n), 1)
			term = optimum_polynomial(k, n)
			if term != reference:
				ans += term
				break
	return str(ans.numerator) + ("" if ans.denominator == 1 else "/" + str(ans.denominator))


def optimum_polynomial(k, n):
	# Lagrange interpolation
	sum = Fraction(0, 1)
	for i in range(k + 1):
		product = Fraction(generating_function(i), 1)
		for j in range(1, k + 1):
			if j != i:
				product *= Fraction(n - j, i - j)
		sum += product
	return sum


def generating_function(n):
	return sum((-n)**i for i in range(DEGREE + 1))


if __name__ == "__main__":
	print(compute())
