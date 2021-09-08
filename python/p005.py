# 
# Solution to Project Euler problem 5
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import math


# The smallest number n that is evenly divisible by every number in a set {k1, k2, ..., k_m}
# is also known as the lowest common multiple (LCM) of the set of numbers.
# The LCM of two natural numbers x and y is given by LCM(x, y) = x * y / GCD(x, y).
# When LCM is applied to a collection of numbers, it is commutative, associative, and idempotent.
# Hence LCM(k1, k2, ..., k_m) = LCM(...(LCM(LCM(k1, k2), k3)...), k_m).
def compute():
	ans = 1
	for i in range(1, 21):
		ans *= i // math.gcd(i, ans)
	return str(ans)


if __name__ == "__main__":
	print(compute())
