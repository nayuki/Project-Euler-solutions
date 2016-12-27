# 
# Solution to Project Euler problem 111
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	DIGITS = 10
	
	primes = eulerlib.list_primes(eulerlib.sqrt(10**DIGITS))
	
	# Only valid if 1 < n <= 10^DIGITS.
	def is_prime(n):
		end = eulerlib.sqrt(n)
		for p in primes:
			if p > end:
				break
			if n % p == 0:
				return False
		return True
	
	
	ans = 0
	# For each repeating digit
	for digit in range(10):
		
		# Search by the number of repetitions in decreasing order
		for rep in range(DIGITS, -1, -1):
			sum = 0
			digits = [0] * DIGITS
			
			# Try all possibilities for filling the non-repeating digits
			for i in range(9**(DIGITS - rep)):
				
				# Build initial array. For example, if DIGITS=7, digit=5, rep=4, i=123, then the array will be filled with 5,5,5,5,1,4,7.
				for j in range(rep):
					digits[j] = digit
				temp = i
				for j in range(DIGITS - rep):
					d = temp % 9
					if d >= digit:  # Skip the repeating digit
						d += 1
					if j > 0 and d > digits[DIGITS - j]:  # If this is true, then after sorting, the array will be in an already-tried configuration
						break
					digits[-1 - j] = d
					temp //= 9
				
				else:
					digits.sort()  # Start at lowest permutation
				
					while True:  # Go through all permutations
						if digits[0] > 0:  # Skip if the number has a leading zero, which means it has less than DIGIT digits
							num = int("".join(map(str, digits)))
							if is_prime(num):
								sum += num
						if not eulerlib.next_permutation(digits):
							break
			
			if sum > 0:  # Primes found; skip all lesser repetitions
				ans += sum
				break
	
	return str(ans)


if __name__ == "__main__":
	print(compute())
