# 
# Solution to Project Euler problem 243
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, fractions


# == Introduction ==
# 
# From the problem statement, a fraction n/d is resilient if and only if n and d are coprime.
# The value totient(i) equals the number of i's in the range [1, d-1] such that i and d are coprime.
# We can see this value is useful because the resilience of a denominator d,
# denoted as R(d), is equal to totient(d) / (d - 1).
# The problem gives us a target value and asks us to find the smallest d such that R(d) < target.
# 
# We know that totient(d) can be calculated from d's prime factorization.
# Namely if d = p1^k1 * p2^k2 * ... p_m^k_m where each p_i is unique and k_i >= 1,
# then totient(d) = (p1 - 1) p1^(k1 - 1) * ... * (p_m - 1) p_m^(k_m - 1).
# 
# 
# == Contiguous prime factors ==
# 
# In the context of this problem, we argue that it is never advantageous to skip a prime factor when
# searching for the smallest d. To phrase it formally, if we have some d = c * p^0 * q^k, where
# {p < q, k >= 1, and c >= 1 is coprime with p and q (i.e. c consists of the other prime factors)},
# then let d' = c * p^k * q^0 and so d' < d, and we shall show that R(d') <= R(d). Here's a long proof:
# 
# We examine the two values
#   R(d') = (totient(c) * (p - 1) * p^(k - 1)) / (c * p^k - 1) = constant * f(p, c, k) and
#   R(d ) = (totient(c) * (q - 1) * q^(k - 1)) / (c * q^k - 1) = constant * f(q, c, k),
#   where f(x, c, k) = (x - 1) x^(k - 1) / (c * x^k - 1).
# 
# Given the crucial fact that p < q, if we want to show that R(d') <= R(d) then it is
# sufficient to show that f(x, c, k) is a non-strictly increasing function with respect to x
# (with c and k being fixed). To achieve this, we take the partial derivative with respect
# to x and derive the fact that it is always non-negative. So we begin:
# 
#   d/dx f(x, c, k) = [x^(k - 2) * (c*x^k - x*k + k - 1)] / [(c*x^k - 1)^2].
#   (omitting a tedious but elementary algebraic derivation)
# 
# Because x >= 2 and c >= 1, clearly the part of the denominator (c*x^k - 1) > 0.
# Also, clearly part of the numerator x^(k - 2) > 0.
# So next we tackle the (c*x^k - x*k + k - 1) part indirectly.
# 
# The following lemma is a special case of Bernoulli's inequality.
# For all k >= 0, x^k >= 1 + k*(x - 1). Proof by induction:
#   Base case, k = 0:
#     x^k = x^0 = 1.  (left-hand side)
#     1 + k*(x - 1) = 1 + 0*(x - 1) + 1.  (RHS)
#     1 >= 1.
#   Induction step, k >= 0:
#     x^k >= 1 + k*(x - 1).  (by assumption)
#     x * x^k >= x * (1 + k*(x - 1)).
#     x^(k + 1) >= x + x*k*(x - 1).  (a)
#     (x - 1)^2 >= 0.  (squares are non-negative)
#     x^2 - 2*x + 1 >= 0.
#     k*x^2 - 2*k*x + k >= 0.
#     k*x^2 - k*x >= k*x - k.
#     x + k*x^2 - k*x >= x + k*x - k
#                      = 1 + x + k*x - k - 1
#                      = 1 + (k + 1)(x - 1).
#     x + x*k*(x - 1) >= 1 + (k + 1)(x - 1).  (b)
#     x^(k + 1) >= 1 + (k + 1)(x - 1).  (combine (a) and (b))
# 
# Now we deduce: c*x^k - x*k + k - 1
#   >= x^k - x*k + k - 1  (because c >= 1)
#   >= (1 + k*(x - 1)) - x*k + k - 1 = 0.  (using the x^k inequality lemma)
# 
# To summarize: x^(k - 2) > 0, (c*x^k - x*k + k - 1) >= 0, and (c x^k - 1)^2 > 0. Therefore we
# conclude that f'(x, c, k) >= 0 for all real x > 1, which means the function is non-strictly increasing
# when x increases. Hence with p < q, we have f(p, c, k) <= f(q, c, k), and finally that R(d') <= R(d).
# 
# The consequence of this lemma is that for any particular target, {the minimum value of d
# satisfying R(d) < target} will have the property that d uses all of the smallest prime factors
# up to a certain point (with at least 1 copy of each factor). For example, d = 2 * 3 * 5 could be
# a minimum value, and d = 2^4 * 3^2 * 5 * 7 might be one. But d = 2 * 5^8 cannot be a minimum value
# because it skips the prime number 3; the alternative d = 2 * 3^8 would be a smaller candidate.
# 
# 
# == Pseudo-resilience ==
# 
# For the sake of the mathematical argument, let's define a related value called the pseudo-resilience:
# 
#   R'(d) = totient(d) / d  (we divide by d instead of d-1)
#         = ((p1 - 1) p1^(k1 - 1) * ... * (p_m - 1) p_m^(k_m - 1)) / (p1^k1 * ... * p_m^k_m)
#         = (p1 - 1)/p1 * (p2 - 1)/p2 * ... (p_m - 1)/p_m.
# 
# Notice that this value is independent of the exponents k_i (as long as each k_i >= 1)!
# Hence the value R'(d) only depends on d's set of unique prime factors, not the multiplicity of
# each factor. Each new prime factor p decreases the pseudo-resilience R'(d) by a factor of (p - 1) / p
# (equivalent to 1 - 1/p). Note that smaller values of p cause a larger decrease.
# 
# The pseudo-resilience is always smaller than the resilience, i.e. R'(d) < R(d), so we
# can use it as a lower bound. For an arbitrary number d, if d is composed entirely of
# copies of the first n primes (e.g. d = 2^4 * 3^2 * 5 is composed of the first 3 primes),
# then R'(p1 * p2 * ... * p_n) = R'(d) < R(d). This means that if we restrict ourselves to
# only using the first n primes to build up d, then R(d) can never be smaller than the value
# R'(p1 * p2 * ... * p_n). So if we look at R'(...) for products of primes (namely primorials),
# we can derive a lower bound on at least how many distinct prime factors d must contain in
# order to make R(d) possibly fall below the target.
# 
# Suppose d's prime factorization contains all of the first n prime numbers (i.e. one or more
# copies of each), and b is another integer such that the set of the first n prime numbers contains
# all of the factors of b. (For example: n = 3, d = 2^4 * 3^2 * 5, b = 2 * 5^9.) Then it is easy
# to show that R'(b*d) = R'(d) (the extra factors have no effect), and then R(b*d) <= R(d) because:
# 
#   R(b*d) = b * totient(d) / (b*d - 1).
#   R(d)   = totient(d) / (d - 1).
#   R(b*d) / R(d) = (b * (d - 1)) / (b*d - 1)
#                 = (b*d - b) / (b*d - 1)
#                 = 1 - (b - 1) / (b*d - 1)
#                <= 1.  (because b >= 1)
# 
# We could make b arbitrary large (e.g. by setting b = 2^k), and this will make R(b*d)
# asymptotically approach R'(b*d) (equivalently R'(d)) from above. But there is no benefit
# to making b larger past a certain point, because multiplying d by the next prime factor q
# will decrease the resilience by more than any value of b ever could. This is because:
# 
#   d > 1. -d < -1.
#   q*d - d < q*d - 1.
#   (q - 1)*d < (q*d - 1).
#   (q - 1) / (q*d - 1) < 1 / d.
#   (q - 1) * totient(d) / (q*d - 1) < totient(d) / d.
#   R(q*d) < R'(d).  (by substitution)
#   Also, R'(d) = R'(b*d) < R(b*d) = b * totient(d) / (b*d - 1).
#   Therefore R(q*d) < R(b*d) for any b whose set of prime factors is already contained in d.
# 
# 
# == The algorithm ==
# 
# First we compute R'(primorial(n)) for n = 1, 2, 3, ... until R'(primorial(n)) < target.
# This gives us a lower bound, telling us that {the answer d}'s factorization needs to
# contain at least all of the first n prime numbers in order to achieve R(d) < target
# (this is a necessary but not sufficient condition). We let d = primorial(n).
# 
# With this lower bound in hand, we test values of b = 1, 2, ..., prime(n+1)-1 (inclusive)
# and try to find the smallest b (if any) such that R(b*d) < target.
# If we succeed, then b*d is the answer. Otherwise we increment n and try again.
def compute():
	TARGET = fractions.Fraction(15499, 94744)
	totient = 1
	denominator = 1
	p = 2
	while True:
		totient *= p - 1
		denominator *= p
		# Note: At this point in the code, denominator is the product of one copy of each
		# prime number up to and including p, totient is equal to totient(denominator),
		# and totient/denominator = R'(2 * 3 * ... * p) (the pseudo-resilience).
		
		# Advance to the next prime
		while True:
			p += 1
			if eulerlib.is_prime(p):
				break
		
		# If the lower bound is below the target, there might be a suitable solution d such that
		# d's factorization only contains prime factors strictly below the current (advanced) value of p
		if fractions.Fraction(totient, denominator) < TARGET:
			# Try to find the lowest factor i such that R(i*d) < TARGET, if any.
			# Note that over this range of i, we have R'(d) = R'(i*d) < R(i*d).
			for i in range(1, p):
				numer = i * totient
				denom = i * denominator
				if fractions.Fraction(numer, denom - 1) < TARGET:
					return str(denom)


if __name__ == "__main__":
	print(compute())
