# 
# Solution to Project Euler problem 133
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


# Repunit formula: R(k) = (10^k - 1) / 9. (Using geometric series)
# 
# For the rest of the argument, let n be an arbitrary integer that is coprime with 10.
# 
# Let k = A(n) be the smallest positive integer such that R(k) = 0 mod n.
# From problem #129, we know k exists and satisfies 1 <= k <= n.
# 
# Lemma: For each natural number m, R(m) = 0 mod n if and only if m is a multiple of k.
# Proof:
#   Backward direction:
#     Assume m is a multiple of k. Then factorize m = jk, where j is an integer.
#     Look at R(m) = R(jk) = 1...1 ... 1...1 (j groups of k 1's) = 10...010...010...01 * R(k) (informally)
#                  = (sum of 10^(ik) for i = 0 to s-1) * R(k).
#     We already have R(k) = 0 mod n, thus (sum of 10^(ik) for i = 0 to s-1) * R(k) = R(m) = 0 mod n.
#   Forward direction (by converse):
#     Assume m is not a multiple of k. Suppose for contradiction that R(m) = 0 mod n.
#     Similar the previous argument, we can zeroize blocks of k 1's while preserving the value of R(m) mod n.
#     Namely, we delete the top k 1's by subtracting R(k) * 10^(m-k), which is 0 mod n because R(k) = 0 mod n.
#     After repeated deletion of the most significant 1's, we can get m' = m mod k, so that 0 < m' < k.
#     (m' != 0 because we assumed m is not a multiple of k.) But with R(m') = R(m) = 0 mod n, and m' < k,
#     this contradicts the definition of k = A(n), the smallest value such that R(k) = 0 mod n.
#     Hence the supposition that R(m) = 0 mod n is false.
# 
# Does there exist an x such that R(10^x) is a multiple of n? By the lemma, this is true if and only if
# there exists an x such that 10^x is a multiple of k. This means k must be a product of 2's and 5's.
# 
# Actually, we don't need to compute k = A(n) to perform this test. If k = 2^a * 5^b, then all sufficiently large
# powers of 10 are a multiple of k. (If k has other prime factors, then no power of 10 is a multiple of k.)
# We know 1 <= k < n, so in this problem 1 <= k < 10^5. For k in this range, the largest exponent among a and b is 16
# (for the number 2^16 = 65536). (In general, the largest exponent is floor(log2(limit)); in this case limit = 10^5.)
# So we only need to test if 10^16 is a multiple of k, equivalent to testing if R(10^16) is a multiple of n.
def compute():
	primes = eulerlib.list_primes(100000)
	ans = sum(p for p in primes if p == 2 or p == 5 or not has_divisible_repunit(p))
	return str(ans)


# Tests whether there exists a k such that R(10^k) is a multiple of p
def has_divisible_repunit(p):
	return (pow(10, 10**16, p * 9) - 1) // 9 % p == 0


if __name__ == "__main__":
	print(compute())
