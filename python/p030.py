# 
# Solution to Project Euler problem 30
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import sys
if sys.version_info.major == 2:
	range = xrange


def compute():
	# As stated in the problem, 1 = 1^5 is excluded.
	# If a number has at least n >= 6 digits, then even if every digit is 9,
	# n * 9^5 is still less than the number (which is at least 10^n).
        # Since 6 * 9^5 = 354,294 and 999,999 > 354,294, there can be no 
        # 6 digit number greater than 354,294 that is a fifth power digit sum
	ans = sum(i for i in range(2, 354294) if i == fifth_power_digit_sum(i))
	return str(ans)


def fifth_power_digit_sum(n):
	return sum(int(c)**5 for c in str(n))


if __name__ == "__main__":
	print(compute())
